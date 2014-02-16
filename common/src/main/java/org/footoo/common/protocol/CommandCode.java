/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.protocol;

/**
 * 命令码
 * 
 * @author fengjing.yfj
 * @version $Id: CommandCode.java, v 0.1 2014年2月12日 上午10:33:32 fengjing.yfj Exp $
 */
public enum CommandCode {
    /** 心跳包 */
    HEART_BEAT(1, "心跳包"),
    /** ECHO */
    ECHO(2, "echo"), ;

    /** 命令码 */
    private final int    code;

    /** 命令说明 */
    private final String info;

    /**
     * 默认构造器
     * 
     * @param code 命令码
     * @param info 命令码说明
     */
    private CommandCode(int code, String info) {
        this.code = code;
        this.info = info;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>info</tt>.
     * 
     * @return property value of info
     */
    public String getInfo() {
        return info;
    }
}
