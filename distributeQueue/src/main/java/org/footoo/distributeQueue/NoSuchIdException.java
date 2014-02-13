/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.distributeQueue;

/**
 * 没有对应的message的ID异常
 * 
 * @author fengjing.yfj
 * @version $Id: NoSuchIdException.java, v 0.1 2014年2月13日 上午10:58:50 fengjing.yfj Exp $
 */
public class NoSuchIdException extends Exception {

    /** 序列号 */
    private static final long serialVersionUID = -3983607133301436675L;

    /**
     * 
     */
    public NoSuchIdException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public NoSuchIdException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NoSuchIdException(Throwable cause) {
        super(cause);
    }

}
