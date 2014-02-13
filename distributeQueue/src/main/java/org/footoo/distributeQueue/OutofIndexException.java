/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.distributeQueue;

/**
 * 索引越界异常
 * 
 * @author fengjing.yfj
 * @version $Id: OutofIndexExceptino.java, v 0.1 2014年2月12日 下午7:55:07 fengjing.yfj Exp $
 */
public class OutofIndexException extends RuntimeException {

    /** 序列号  */
    private static final long serialVersionUID = -1815665863721655343L;

    public OutofIndexException() {
        super();
    }

    public OutofIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutofIndexException(String message) {
        super(message);
    }

    public OutofIndexException(Throwable cause) {
        super(cause);
    }

}
