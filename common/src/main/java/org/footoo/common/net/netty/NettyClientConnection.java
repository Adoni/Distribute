/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import org.footoo.common.exception.DistributeCommonException;
import org.footoo.common.exception.NetException;
import org.footoo.common.exception.NetTimeoutException;
import org.footoo.common.net.CommandInvokedCallback;
import org.footoo.common.protocol.CommandPackage;

/**
 * 客户端连接
 * 
 * @author fengjing.yfj
 * @version $Id: NettyClientConnection.java, v 0.1 2014年2月15日 下午7:11:22 fengjing.yfj Exp $
 */
public interface NettyClientConnection extends NettyConnection {
    /**
     * 同步的方式调用请求
     * 
     * @param commandPackage 请求包
     * @param timeoutms 超时时间（ms)
     * @return 处理的结果的包
     * @throws NetTimeoutException
     * @throws NetException
     * @throws DistributeCommonException 
     */
    public CommandPackage invokeCommandSync(CommandPackage commandPackage, int timeoutms)
                                                                                         throws NetTimeoutException,
                                                                                         NetException,
                                                                                         DistributeCommonException;

    /**
     * 异步的方式调用请求
     * 
     * @param commandPackage 请求包
     * @param timeoutms 超时时间(ms)
     * @param callback 回调函数
     */
    public void invokeCommandAsync(CommandPackage commandPackage, int timeoutms,
                                   CommandInvokedCallback callback);
}
