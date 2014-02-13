/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.distributeQueue;

import org.footoo.common.tools.JsonSerializable;
import org.footoo.mainServer.InvalidMessageIdException;
import org.footoo.mainServer.MessageID;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: MessageIDTest.java, v 0.1 2014年2月13日 下午4:03:09 fengjing.yfj Exp $
 */
public class MessageIDTest {
    public static void main(String args[]) throws InvalidMessageIdException {
        MessageID messageID = new MessageID();
        messageID.generateSelfId();
        System.out.println(messageID + " " + ("" + messageID).length());

        System.out.println(Integer.toHexString((byte) -1));

        for (int i = 0; i < 10; i++) {
            MessageID messageID2 = new MessageID();
            messageID2.generateSelfId();

            String s = JsonSerializable.serialize(messageID2);
            System.out.println(messageID2);
            System.out.println(s);
            System.out.println(JsonSerializable.deserialize(s, MessageID.class));
        }

        System.out.println("=================");
        for (int i = 0; i < 10; i++) {
            MessageID messageID3 = new MessageID();
            messageID3.generateSelfId();
            String s = messageID3.toString();
            System.out.println(messageID3 + " " + new MessageID(s));
        }
    }
}
