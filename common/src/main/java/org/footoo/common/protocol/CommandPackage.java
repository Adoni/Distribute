/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.protocol;

import java.util.Map;

/**
 * 通信的命令包
 * 这是整个通信过程的最小单元
 * 
 * @author fengjing.yfj
 * @version $Id: CommandPackage.java, v 0.1 2014年2月12日 上午10:21:29 fengjing.yfj Exp $
 */
public class CommandPackage {
    /** 版本 */
    private int                 version;
    /** 代码,可以是命令码或者是错误码 */
    private int                 code;
    /** 说明 */
    private String              info;
    /** 自定义扩展字段 */
    private Map<Object, Object> extInfo;
    /** 用于连接复用,一般使用的是线程的pthreadID */
    private int                 opaque;
    /** 实际存放的数据 */
    private byte[]              body;

    /**
     * 默认的构造器
     */
    public CommandPackage() {

    }

    /**
     * 构造命令包的构造器
     * 
     * @param commandCode 命令码
     * @param extInfo
     * @param opaque
     * @param body
     */
    public CommandPackage(CommandCode commandCode, Map<Object, Object> extInfo, int opaque,
                          byte[] body) {
        this.version = ProtocolVersion.VERSION;
        if (commandCode != null) {
            this.code = commandCode.getCode();
            this.info = commandCode.getInfo();
        }
        this.extInfo = extInfo;
        this.opaque = opaque;
        this.body = body;
    }

    /**
     * 构造响应包的构造器
     * 
     * @param commandErrorCode 命令处理结果的错误码
     * @param extInfo
     * @param opaque
     * @param body
     */
    public CommandPackage(CommandErrorCode commandErrorCode, Map<Object, Object> extInfo,
                          int opaque, byte[] body) {
        this.version = ProtocolVersion.VERSION;
        if (commandErrorCode != null) {
            this.code = commandErrorCode.getCode();
            this.info = commandErrorCode.getInfo();
        }
        this.extInfo = extInfo;
        this.opaque = opaque;
        this.body = body;
    }

    /** 
     * 添加扩展字段
     * 
     * @param key
     * @param value
     */
    public void addExtInfo(Object key, Object value) {
        extInfo.put(key, value);
    }

    /**
     * 移除一项扩展字段
     * 
     * @param key
     */
    public void delExtInfo(Object key) {
        extInfo.remove(key);
    }

    /**
     * Getter method for property <tt>version</tt>.
     * 
     * @return property value of version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter method for property <tt>version</tt>.
     * 
     * @param version value to be assigned to property version
     */
    public void setVersion(int version) {
        this.version = version;
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
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>info</tt>.
     * 
     * @return property value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter method for property <tt>info</tt>.
     * 
     * @param info value to be assigned to property info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     * 
     * @return property value of extInfo
     */
    public Map<Object, Object> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     * 
     * @param extInfo value to be assigned to property extInfo
     */
    public void setExtInfo(Map<Object, Object> extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * Getter method for property <tt>opaque</tt>.
     * 
     * @return property value of opaque
     */
    public int getOpaque() {
        return opaque;
    }

    /**
     * Setter method for property <tt>opaque</tt>.
     * 
     * @param opaque value to be assigned to property opaque
     */
    public void setOpaque(int opaque) {
        this.opaque = opaque;
    }

    /**
     * Getter method for property <tt>body</tt>.
     * 
     * @return property value of body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * Setter method for property <tt>body</tt>.
     * 
     * @param body value to be assigned to property body
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

}
