/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.protocol;

/**
 * 命令处理后的错误码
 * 
 * @author fengjing.yfj
 * @version $Id: CommandErrorCode.java, v 0.1 2014年2月12日 上午10:33:49 fengjing.yfj Exp $
 */
public enum CommandErrorCode {
    /** 处理成功 */
    OK(0, "处理成功"),
    /** 服务端内部错误 */
    SERVER_INNER_ERROR(1, "服务端内部错误"), ;

    /** 错误码 */
    private final int    code;

    /** 错误说明 */
    private final String info;

    /**
     * 默认构造器
     * 
     * @param code 错误码
     * @param info 错误码说明
     */
    private CommandErrorCode(int code, String info) {
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
