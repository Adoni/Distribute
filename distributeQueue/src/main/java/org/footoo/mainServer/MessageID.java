/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每一个信息的ID，这是内部数据表示
 * 展现给用户的是一个16进制的字符串
 * 
 * @author fengjing.yfj
 * @version $Id: MessageID.java, v 0.1 2014年2月13日 下午3:02:45 fengjing.yfj Exp $
 */
public class MessageID {
    //实际的ID的字节数
    private transient static final int           MESSAGE_ID_LEN = 14;
    //实际的ID
    private byte                                 id[];
    //序列
    private transient static final AtomicInteger serialize      = new AtomicInteger();

    /**
     * 默认构造器
     */
    public MessageID() {
        id = null;
    }

    /**
     * 
     * 
     * @param id
     * @throws InvalidMessageIdException
     */
    public MessageID(byte[] id) throws InvalidMessageIdException {
        setId(id);
    }

    /**
     * 拷贝构造函数，但是注意拷贝的时候ID是相同的，对于不同的信息
     * 一定不要拷贝id
     * 
     * @param other
     * @throws InvalidMessageIdException
     */
    public MessageID(MessageID other) throws InvalidMessageIdException {
        this(other.id);
    }

    public MessageID(String id) throws InvalidMessageIdException {
        this(toId(id));
    }

    /**
     * 为自己生成messageID
     * 
     * @throws InvalidMessageIdException
     */
    public void generateSelfId() throws InvalidMessageIdException {
        setId(generateID());
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public byte[] getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     * @throws InvalidMessageIdException 
     */
    public void setId(byte[] id) throws InvalidMessageIdException {
        if (id != null && id.length != MESSAGE_ID_LEN) {
            throw new InvalidMessageIdException("message ID的长度必须为" + MESSAGE_ID_LEN);
        }
        this.id = id;
    }

    /**
     * 将message ID的字符串表示，转换为内部表示
     * 
     * @param id
     * @return
     * @throws InvalidMessageIdException
     */
    private static byte[] toId(String id) throws InvalidMessageIdException {
        byte ret[] = new byte[MESSAGE_ID_LEN];

        if (id == null) {
            return null;
        }
        //长度校验
        if (id.length() != MESSAGE_ID_LEN * 2) {
            throw new InvalidMessageIdException("message ID[" + id + "]的长度必须为" + MESSAGE_ID_LEN * 2);
        }
        //进行转化
        try {
            for (int i = 1; i <= MESSAGE_ID_LEN; i++) {
                ret[MESSAGE_ID_LEN - i] = (byte) Integer.parseInt(id.substring(2 * i - 2, 2 * i),
                    16);
            }
        } catch (Exception e) {
            throw new InvalidMessageIdException("message ID[" + id + "]不合法", e);
        }
        return ret;
    }

    /**
     * 生成一个id
     * 8字节的时间值，4字节的序列数，2字节的随机值
     * 
     * @return
     */
    public static byte[] generateID() {
        byte[] ret = new byte[MESSAGE_ID_LEN];

        //产生一个时间值
        long time = System.currentTimeMillis();
        //产生一个序列数
        int s = serialize.getAndIncrement();
        //产生一个short的随机值
        short ran = (short) (Math.random() * Short.MAX_VALUE);

        //将时间值拷贝到结果的最高8字节
        for (int i = 1; i <= 8; i++) {
            ret[MESSAGE_ID_LEN - i] = (byte) ((time >>> (64 - 8 * i)) & 0xFF);
        }
        //将序列数拷贝到接下来的4字节
        for (int i = 1; i <= 4; i++) {
            ret[MESSAGE_ID_LEN - i - 8] = (byte) ((s >>> (32 - 8 * i)) & 0xFF);
        }
        //将随机值拷贝到接下来的2字节
        for (int i = 1; i <= 2; i++) {
            ret[MESSAGE_ID_LEN - i - 12] = (byte) ((ran >>> (16 - 8 * i)) & 0xFF);
        }
        return ret;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (id == null) {
            return null;
        }
        //长度必须是固定的
        assert (id.length == MESSAGE_ID_LEN);

        StringBuffer buffer = new StringBuffer();

        for (int i = MESSAGE_ID_LEN - 1; i >= 0; i--) {
            String hex = Integer.toHexString(id[i]);
            hex = hex.length() > 1 ? hex : ("0" + hex);
            hex = hex.substring(0, 2);
            buffer.append(hex);
        }
        return buffer.toString();
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id == null ? 0 : bytesCode(id);
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        //类型检查
        if (other == null || !(other instanceof MessageID)) {
            return false;
        }
        //必须都有id
        if (id == null || ((MessageID) other).id == null) {
            return false;
        }
        //id比较
        return bytesEqual(id, ((MessageID) other).id);

    }

    /**
     * 比较byte数组是否相等
     * 
     * @param a
     * @param b
     * @return
     */
    private boolean bytesEqual(byte[] a, byte[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算a的hash值
     * 
     * @param a
     * @return
     */
    private int bytesCode(byte[] a) {
        int ret = 1;
        for (int i = 0; i < a.length; i++) {
            ret *= a[i];
        }
        return ret * 17;
    }
}
