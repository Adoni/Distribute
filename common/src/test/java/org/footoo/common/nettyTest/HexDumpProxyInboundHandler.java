/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: HexDumpProxyInboundHandler.java, v 0.1 2014年2月13日 下午8:12:42 fengjing.yfj Exp $
 */
public class HexDumpProxyInboundHandler extends SimpleChannelUpstreamHandler {
    private final ClientSocketChannelFactory cf;
    private final String                     remoteHost;
    private final int                        remotePort;
    private volatile Channel                 outboundChannel;

    public HexDumpProxyInboundHandler(ClientSocketChannelFactory cf, String remoteHost,
                                      int remotePort) {
        this.cf = cf;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
        final Channel inboundChannel = e.getChannel();
        inboundChannel.setReadable(false);
        ClientBootstrap cb = new ClientBootstrap(cf);
        cb.getPipeline().addLast("handler", new OutboundHandler(e.getChannel()));
        ChannelFuture future = cb.connect(new InetSocketAddress(remoteHost, remotePort));
        outboundChannel = future.getChannel();
        future.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    inboundChannel.setReadable(true);
                } else {
                    inboundChannel.close();
                }
            }
        });
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer msg = (ChannelBuffer) e.getMessage();
        System.out.println(">>>" + ChannelBuffers.hexDump(msg));
        outboundChannel.write(msg);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        closeOnFlush(e.getChannel());
    }

    private static class OutboundHandler extends SimpleChannelUpstreamHandler {
        private final Channel inboundChannel;

        public OutboundHandler(Channel inboundChannel) {
            this.inboundChannel = inboundChannel;
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
            ChannelBuffer msg = (ChannelBuffer) e.getMessage();
            System.out.println("<<<" + ChannelBuffers.hexDump(msg));
            inboundChannel.write(msg);
        }

        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
            closeOnFlush(inboundChannel);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
            e.getCause().printStackTrace();
            closeOnFlush(e.getChannel());
        }

    }

    static void closeOnFlush(Channel ch) {
        if (ch.isConnected()) {
            ch.write(ChannelBuffers.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
