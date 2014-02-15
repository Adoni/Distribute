/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.nettyTest3;

import java.util.Date;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: UnixTime.java, v 0.1 2014年2月14日 下午1:31:14 fengjing.yfj Exp $
 */
public class UnixTime {
    private final int value;

    public UnixTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date(value * 1000L).toString();
    }
}
