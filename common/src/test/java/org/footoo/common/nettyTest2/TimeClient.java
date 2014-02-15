/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: TimeClient.java, v 0.1 2014年2月14日 下午12:56:12 fengjing.yfj Exp $
 */
public class TimeClient {
    public static void main(String args[]) {
        String host = "127.0.0.1";
        int port = 8090;

        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.getPipeline().addLast("handler", new TimeClientHandler());
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        bootstrap.connect(new InetSocketAddress(host, port));
    }
}
