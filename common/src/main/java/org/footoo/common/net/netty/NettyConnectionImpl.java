/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.footoo.common.exception.DistributeCommonException;
import org.footoo.common.exception.NetException;
import org.footoo.common.exception.NetTimeoutException;
import org.footoo.common.net.CommandInvokedCallback;
import org.footoo.common.net.SendedCallback;
import org.footoo.common.protocol.CommandPackage;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * netty 的connection的实现，对于同一个（地址，端口）复用一个连接，不管是多少个线程
 * 
 * @author fengjing.yfj
 * @version $Id: NettyConnectionImpl.java, v 0.1 2014年2月14日 下午2:40:01 fengjing.yfj Exp $
 */
public class NettyConnectionImpl implements NettyConnection {

    /** 正在发送的报文的缓冲区 */
    private ConcurrentHashMap<ChannelFuture, SendingPackageInfo> sendingPackages  = new ConcurrentHashMap<ChannelFuture, SendingPackageInfo>();

    /** 用来等待响应的报文 */
    private ConcurrentHashMap<Long, SendingPackageInfo>          responsePackages = new ConcurrentHashMap<Long, SendingPackageInfo>();

    /** 目标的地址 */
    private String                                               addr             = "127.0.0.1";

    /** 连接的端口 */
    private int                                                  port             = 1234;

    /** 对应的实际的channel */
    private Channel                                              channel;

    /** channel Factory */
    private ChannelFactory                                       channelFactory;

    /** 监听器列表 */
    private ConcurrentHashMap<NettyListener, NettyListener>      listeners        = new ConcurrentHashMap<NettyListener, NettyListener>();

    /** 状态 */
    private Status                                               status           = Status.INIT;

    /**
     * 构造器
     * 
     * @param addr
     */
    public NettyConnectionImpl(String addr) {
        this.addr = addr;
    }

    /**
     * 构造器
     * 
     * @param port
     */
    public NettyConnectionImpl(int port) {
        this.port = port;
    }

    public NettyConnectionImpl(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public NettyConnectionImpl(Channel channel) {
        this.channel = channel;
        InetSocketAddress socketAddress = (InetSocketAddress) channel.getRemoteAddress();
        //已经设置了地址
        if (socketAddress != null) {
            this.addr = socketAddress.getAddress().getHostAddress();
            this.port = socketAddress.getPort();
        }
    }

    public NettyConnectionImpl(Channel channel, String addr, int port) {
        this.channel = channel;
        this.port = port;
        this.addr = addr;
    }

    /**
     * 同步的方式启动
     * 
     * @throws DistributeCommonException 
     */
    public void startSync() throws DistributeCommonException {
        status = Status.STARTING;
        if (channel == null) {
            if (channelFactory == null) {
                Executor executor = Executors.newFixedThreadPool(6);
                channelFactory = new NioClientSocketChannelFactory(executor, executor);
            }
            ClientBootstrap clientBootstrap = new ClientBootstrap(channelFactory);
            ChannelFuture future = clientBootstrap.connect(new InetSocketAddress(addr, port));

            future.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture f) throws Exception {
                    if (!f.isSuccess()) {
                        throw new NetException(f.getCause(), "连接[" + addr + ":" + port + "]失败");
                    } else {
                        //设置channel
                        channel = f.getChannel();
                    }
                }
            });

            //等待连接完成
            try {
                future.await();
            } catch (InterruptedException e) {
                throw new DistributeCommonException(e);
            }

        } else if (!channel.isConnected()) {
            channel.connect(new InetSocketAddress(addr, port));
        }
        status = Status.RUNNING;
    }

    /**
     * 关闭
     */
    public void shutDown() {

    }

    /**
     * 获取状态
     * 
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 是否正在运行
     * 
     * @return
     */
    public boolean isRunning() {
        return status == Status.RUNNING;
    }

    /** 防止多次调用 */
    private boolean invokedRecvNewPackage = false;

    /**
     * 调用接受到新的包时的回调函数
     * 注意，只要一有包含长度的报文到达，就会调用这个函数
     * 所以这个函数必须使用invokedRecvNewPackage
     * 
     * @param len
     */
    public void invokeRecvNewPackageSync(int len) {
        //还是在接受同一个包
        if (invokedRecvNewPackage) {
            return;
        }
        //调用同步函数
        Enumeration<NettyListener> listenerList = listeners.keys();
        while (listenerList.hasMoreElements()) {
            NettyListener listener = listenerList.nextElement();
            if (listener instanceof NettyRecvPackageListener) {
                ((NettyRecvPackageListener) listener).newPackageComing(len);
            }
        }
    }

    /**
     * 接收到一个完整的包，调用的回调函数
     * 
     * @param commandPackage
     */
    public void invokeRecvFullPackageSync(CommandPackage commandPackage) {
        //可以继续调用接受到新package的同步函数
        invokedRecvNewPackage = false;
        //调用同步函数
        Enumeration<NettyListener> listenerList = listeners.keys();
        while (listenerList.hasMoreElements()) {
            NettyListener listener = listenerList.nextElement();
            if (listener instanceof NettyRecvPackageListener) {
                ((NettyRecvPackageListener) listener).fullPackageReceived(commandPackage);
            }
        }
    }

    /**
     * 发送一个报文前调用的同步函数
     * 
     * @param commandPackage
     */
    public void invokeBeforePackageSendRecv(CommandPackage commandPackage) {
        //调用同步函数
        Enumeration<NettyListener> listenerList = listeners.keys();
        while (listenerList.hasMoreElements()) {
            NettyListener listener = listenerList.nextElement();
            if (listener instanceof NettySendPackageListener) {
                ((NettySendPackageListener) listener).beforePackageSend(commandPackage);
            }
        }
    }

    /**
     * 发送一个报文后调用的同步函数
     * 
     * @param commandPackage
     */
    public void invokeAfterPackageSendRecv(CommandPackage commandPackage) {
        //调用同步函数
        Enumeration<NettyListener> listenerList = listeners.keys();
        while (listenerList.hasMoreElements()) {
            NettyListener listener = listenerList.nextElement();
            if (listener instanceof NettySendPackageListener) {
                ((NettySendPackageListener) listener).afterPackageSend(commandPackage);
            }
        }
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#getDestAddr()
     */
    @Override
    public String getDestAddr() {
        return addr;
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#invokeCommandSync(org.footoo.common.protocol.CommandPackage, int)
     */
    @Override
    public CommandPackage invokeCommandSync(CommandPackage commandPackage, int timeoutms)
                                                                                         throws NetTimeoutException,
                                                                                         NetException {
        //没有在运行，不能发送
        if (!isRunning()) {

        }

        return null;
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#invokeCommandAsync(org.footoo.common.protocol.CommandPackage, int, org.footoo.common.net.CommandInvokedCallback)
     */
    @Override
    public void invokeCommandAsync(CommandPackage commandPackage, int timeoutms,
                                   CommandInvokedCallback callback) {
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#sendResponseSync(org.footoo.common.protocol.CommandPackage, int)
     */
    @Override
    public void sendResponseSync(CommandPackage commandPackage, int timeoutms)
                                                                              throws NetTimeoutException,
                                                                              NetException {
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#sendResponseAsync(org.footoo.common.protocol.CommandPackage, org.footoo.common.net.SendedCallback)
     */
    @Override
    public void sendResponseAsync(CommandPackage commandPackage, SendedCallback callback) {
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#registerNettyListener(org.footoo.common.net.netty.NettyListener)
     */
    @Override
    public void registerNettyListener(NettyListener listener) {
    }

    /** 
     * @see org.footoo.common.net.netty.NettyConnection#unregisterNettyListener(org.footoo.common.net.netty.NettyListener)
     */
    @Override
    public void unregisterNettyListener(NettyListener listener) {
    }

}
