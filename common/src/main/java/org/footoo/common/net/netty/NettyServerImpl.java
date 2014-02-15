/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * netty的服务端
 * 
 * @author fengjing.yfj
 * @version $Id: NettyServerImpl.java, v 0.1 2014年2月15日 下午5:22:26 fengjing.yfj Exp $
 */
public class NettyServerImpl {
    /** 绑定的地址 */
    private String addr;

    /** 绑定的端口 */
    private int    port;

    /** 线程池大小 */
    private int    threadPoolSize = 8;

    /**
     * 启动
     */
    public void start() {
        //线程池
        Executor executor = Executors.newFixedThreadPool(threadPoolSize);
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(executor, executor);
        ServerBootstrap serverBootstrap = new ServerBootstrap(channelFactory);

        //启动
        InetSocketAddress localAddress;
        //设置邦定地址
        if (addr != null) {
            localAddress = new InetSocketAddress(addr, port);
        } else {
            localAddress = new InetSocketAddress(port);
        }
        //绑定地址
        serverBootstrap.bind(localAddress);
    }
}
