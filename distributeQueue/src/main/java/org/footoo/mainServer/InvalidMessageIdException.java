/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

/**
 * 非法的message id
 * 
 * @author fengjing.yfj
 * @version $Id: InvalidMessageIdException.java, v 0.1 2014年2月13日 下午3:40:59 fengjing.yfj Exp $
 */
public class InvalidMessageIdException extends Exception {

    /** 序列号 */
    private static final long serialVersionUID = -4463483508069107360L;

    /**
     * 
     */
    public InvalidMessageIdException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidMessageIdException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InvalidMessageIdException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InvalidMessageIdException(Throwable cause) {
        super(cause);
    }

}
