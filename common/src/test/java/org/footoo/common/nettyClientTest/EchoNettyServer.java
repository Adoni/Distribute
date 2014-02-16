/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyClientTest;

import org.footoo.common.net.netty.NettyServerConnectionImpl;

/**
 * Echo 服务器
 * 
 * @author fengjing.yfj
 * @version $Id: NettyServer.java, v 0.1 2014年2月16日 下午2:02:06 fengjing.yfj Exp $
 */
public class EchoNettyServer {
    public static void main(String args[]) {
        NettyServerConnectionImpl server = new NettyServerConnectionImpl(1234);
        server.registerNettyListener(new ConnectionListener());
        server.start();
    }
}
