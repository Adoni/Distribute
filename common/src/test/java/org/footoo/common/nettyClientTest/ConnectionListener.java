/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyClientTest;

import org.footoo.common.net.netty.NettyConnectionComingListener;
import org.jboss.netty.channel.Channel;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: ConnectionListener.java, v 0.1 2014年2月16日 下午2:47:53 fengjing.yfj Exp $
 */
public class ConnectionListener implements NettyConnectionComingListener {

    /** 
     * @see org.footoo.common.net.netty.NettyConnectionComingListener#connecting(java.lang.String, int, org.jboss.netty.channel.Channel)
     */
    @Override
    public void connecting(String clientAddr, int clientPort, Channel channel) {
        System.out.println("Connection comming [" + clientAddr + ":" + clientPort + "]");
    }

}
