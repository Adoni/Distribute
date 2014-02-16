/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyClientTest;

import org.footoo.common.exception.DistributeCommonException;
import org.footoo.common.net.netty.NettyClientConnection;
import org.footoo.common.net.netty.NettyClientConnectionImpl;
import org.footoo.common.protocol.CommandCode;
import org.footoo.common.protocol.CommandPackage;
import org.footoo.common.tools.JsonSerializable;

/**
 * netty的echo客户端
 * 
 * @author fengjing.yfj
 * @version $Id: EchoNettyClient.java, v 0.1 2014年2月16日 下午2:22:24 fengjing.yfj Exp $
 */
public class EchoNettyClient {
    public static void main(String args[]) throws DistributeCommonException {

        NettyClientConnectionImpl client = new NettyClientConnectionImpl("127.0.0.1", 1234);
        client.startSync();
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(client, i)).start();
        }

    }
}

class Task implements Runnable {

    private NettyClientConnection clientConnection;
    private int                   i;

    public Task(NettyClientConnection clientConnection, int i) {
        this.clientConnection = clientConnection;
        this.i = i;
    }

    @Override
    public void run() {
        CommandPackage commandPackage = new CommandPackage(CommandCode.ECHO, null, Thread
            .currentThread().getId(), JsonSerializable.serialize("hello" + i).getBytes());
        commandPackage.generateOpaque();
        CommandPackage response = null;
        try {
            response = clientConnection.invokeCommandSync(commandPackage, 3 * 1000);
            System.out.println(JsonSerializable.deserialize(new String(response.getBody()),
                String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

}
