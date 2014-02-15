/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: Client.java, v 0.1 2014年2月14日 下午1:44:12 fengjing.yfj Exp $
 */
public class Client {
    public static void main(String args[]) {
        String host = "127.0.0.1";
        int port = 8090;

        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool());

        ClientBootstrap clientBootstrap = new ClientBootstrap(factory);
        clientBootstrap.getPipeline().addLast("decoder", new TimeDecoder());
        clientBootstrap.getPipeline().addLast("handler", new TimeClientHandler());

        clientBootstrap.connect(new InetSocketAddress(host, port));
    }
}
