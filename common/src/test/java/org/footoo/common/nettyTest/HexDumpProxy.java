/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: HexDumpProxy.java, v 0.1 2014年2月13日 下午7:54:06 fengjing.yfj Exp $
 */
public class HexDumpProxy {
    public static void main(String args[]) {
        int localPort = 12345;
        String remoteHost = "220.181.111.86";
        int remotePort = 80;
        Executor executor = Executors.newCachedThreadPool();
        ServerBootstrap sb = new ServerBootstrap(new NioServerSocketChannelFactory(executor,
            executor));

        ClientSocketChannelFactory cf = new NioClientSocketChannelFactory(executor, executor);

        sb.setPipelineFactory(new HexDumpProxyPipelineFactory(cf, remoteHost, remotePort));
        sb.bind(new InetSocketAddress(localPort));
    }
}
