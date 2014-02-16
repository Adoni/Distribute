/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import java.net.InetSocketAddress;

import org.footoo.common.exception.DistributeCommonException;
import org.footoo.common.protocol.CommandErrorCode;
import org.footoo.common.protocol.CommandPackage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * netty服务端的数据处理handler
 * 
 * @author fengjing.yfj
 * @version $Id: NettyServerChannelHandler.java, v 0.1 2014年2月15日 下午7:41:48 fengjing.yfj Exp $
 */
public class NettyServerChannelHandler extends SimpleChannelHandler {
    private NettyServerConnectionImpl nettyServerConnectionImpl;

    public NettyServerChannelHandler(NettyServerConnectionImpl nettyServerConnectionImpl) {
        this.nettyServerConnectionImpl = nettyServerConnectionImpl;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        //请求包
        CommandPackage requestPackage = (CommandPackage) e.getMessage();
        System.out.println("request" + requestPackage);
        CommandHandler handler = CommandHandlerTable.findHandler(requestPackage.getCode());
        //响应结果
        CommandPackage result;
        try {
            result = handler.handle(requestPackage);

        } catch (DistributeCommonException e1) {
            result = new CommandPackage(CommandErrorCode.SERVER_INNER_ERROR, null, 0, null);
        }
        //设置opaque
        result.setOpaque(requestPackage.getOpaque());
        //发送响应结果
        e.getChannel().write(result);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        System.out.println(e);
        Channel channel = e.getChannel();
        //调用同步函数
        nettyServerConnectionImpl.invokeConnectionComingSync(
            ((InetSocketAddress) channel.getRemoteAddress()).getAddress().getHostAddress(),
            ((InetSocketAddress) channel.getRemoteAddress()).getPort(), channel);
    }

}
