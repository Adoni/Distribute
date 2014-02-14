/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: Server.java, v 0.1 2014年2月14日 上午11:36:29 fengjing.yfj Exp $
 */
public class Server {
    public static void main(String args[]) {
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool());

        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        DiscardServerHandler handler = new DiscardServerHandler();

        ChannelPipeline pipeline = bootstrap.getPipeline();
        pipeline.addLast("handler", handler);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        bootstrap.bind(new InetSocketAddress(8090));

    }
}
