/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest2;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: DiscardServerHandler.java, v 0.1 2014年2月14日 上午11:45:47 fengjing.yfj Exp $
 */
public class DiscardServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        /*while (buf.readable()) {
            System.out.println((char) buf.readByte());
        }*/
        e.getChannel().write(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        Channel channel = e.getChannel();

        channel.close();
    }

    @Override
    public void channelConnected(ChannelHandlerContext context, ChannelStateEvent e) {
        Channel channel = e.getChannel();
        //String t = "2014/2/14 12:45";
        ChannelBuffer time = ChannelBuffers.buffer(4);
        //time.writeBytes(t.getBytes());
        time.writeInt((int) (System.currentTimeMillis() / 1000));
        ChannelFuture f = channel.write(time);

        f.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel ch = channelFuture.getChannel();
                ch.close();
            }
        });
    }
}
