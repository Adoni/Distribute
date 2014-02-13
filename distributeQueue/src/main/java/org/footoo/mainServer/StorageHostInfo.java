/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

/**
 * 存储集群的信息
 * 
 * @author fengjing.yfj
 * @version $Id: StorageHostInfo.java, v 0.1 2014年2月13日 下午5:50:57 fengjing.yfj Exp $
 */
public class StorageHostInfo {
    /** 存储集群的组ID, 同一个组ID下的集群数据必须是一致的 */
    private String groupId;
    /** 主机的ID */
    private int    id;
    /** 主机的地址 */
    private String addr;

    /**
     * Getter method for property <tt>groupId</tt>.
     * 
     * @return property value of groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Setter method for property <tt>groupId</tt>.
     * 
     * @param groupId value to be assigned to property groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>addr</tt>.
     * 
     * @return property value of addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * Setter method for property <tt>addr</tt>.
     * 
     * @param addr value to be assigned to property addr
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

}
