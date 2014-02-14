/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: HexDumpProxyPipelineFactory.java, v 0.1 2014年2月13日 下午8:05:48 fengjing.yfj Exp $
 */
public class HexDumpProxyPipelineFactory implements ChannelPipelineFactory {
    private final ClientSocketChannelFactory cf;
    private final String                     remoteHost;
    private final int                        remotePort;

    public HexDumpProxyPipelineFactory(ClientSocketChannelFactory cf, String remoteHost,
                                       int remotePort) {
        this.cf = cf;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    /** 
     * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
     */
    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline p = Channels.pipeline();
        p.addLast("handler", new HexDumpProxyInboundHandler(cf, remoteHost, remotePort));
        return p;
    }

}
