/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import org.footoo.common.exception.NetException;
import org.footoo.common.exception.NetTimeoutException;
import org.footoo.common.net.CommandInvokedCallback;
import org.footoo.common.net.SendedCallback;
import org.footoo.common.protocol.CommandPackage;

/**
 * Netty的一个连接,
 * 一个客户端（相同端口)到另一个客户端（相同端口)应该只使用一个连接
 * 
 * @author fengjing.yfj
 * @version $Id: NettyConnection.java, v 0.1 2014年2月14日 上午10:51:49 fengjing.yfj Exp $
 */
public interface NettyConnection {

    /**
     * 获取连接的目标的地址
     * 
     * @return 目标地址
     */
    public String getDestAddr();

    /**
     * 同步的方式调用请求
     * 
     * @param commandPackage 请求包
     * @param timeoutms 超时时间（ms)
     * @return 处理的结果的包
     * @throws NetTimeoutException
     * @throws NetException
     */
    public CommandPackage invokeCommandSync(CommandPackage commandPackage, int timeoutms)
                                                                                         throws NetTimeoutException,
                                                                                         NetException;

    /**
     * 异步的方式调用请求
     * 
     * @param commandPackage 请求包
     * @param timeoutms 超时时间(ms)
     * @param callback 回调函数
     */
    public void invokeCommandAsync(CommandPackage commandPackage, int timeoutms,
                                   CommandInvokedCallback callback);

    /**
     * 以同步的方式发送响应报文
     * 
     * @param commandPackage 响应报文
     * @param timeoutms 超时时间（ms)
     * @throws NetTimeoutException
     * @throws NetException
     */
    public void sendResponseSync(CommandPackage commandPackage, int timeoutms)
                                                                              throws NetTimeoutException,
                                                                              NetException;

    /**
     * 以异步方式发送响应报文
     * 
     * @param commandPackage 响应报文
     * @param callback 发送结束的回调函数
     */
    public void sendResponseAsync(CommandPackage commandPackage, SendedCallback callback);

    /**
     * 注册监听器
     * 
     * @param listener
     */
    public void registerNettyListener(NettyListener listener);

    /**
     * 注销监听器
     * 
     * @param listener
     */
    public void unregisterNettyListener(NettyListener listener);
}
