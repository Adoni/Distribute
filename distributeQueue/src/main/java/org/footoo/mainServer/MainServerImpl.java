/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.footoo.common.net.RemoteClient;

/**
 * 主服务器的实现
 * 
 * @author fengjing.yfj
 * @version $Id: MainServerImpl.java, v 0.1 2014年2月13日 下午6:43:17 fengjing.yfj Exp $
 */
public class MainServerImpl implements MainServer {
    /** 系统状态 */
    private int                                        status           = INIT;

    /** 存储服务器信息 */
    private Map<String, Map<Integer, StorageHostInfo>> storageHostInfos = new HashMap<String, Map<Integer, StorageHostInfo>>();

    /** 存储从服务器信息 */
    private List<SlaveServerInfo>                      slaveServerInfos = new ArrayList<SlaveServerInfo>();

    /** 网络客户端 */
    private RemoteClient                               client;

    @Override
    public void start() {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public void suspend() {
    }

    @Override
    public void resume() {
    }

    @Override
    public int getStatus() {
        return status;
    }

    /**
     * Getter method for property <tt>storageHostInfos</tt>.
     * 
     * @return property value of storageHostInfos
     */
    public Map<String, Map<Integer, StorageHostInfo>> getStorageHostInfos() {
        return storageHostInfos;
    }

    /**
     * Setter method for property <tt>storageHostInfos</tt>.
     * 
     * @param storageHostInfos value to be assigned to property storageHostInfos
     */
    public void setStorageHostInfos(Map<String, Map<Integer, StorageHostInfo>> storageHostInfos) {
        this.storageHostInfos = storageHostInfos;
    }

    /**
     * Getter method for property <tt>slaveServerInfos</tt>.
     * 
     * @return property value of slaveServerInfos
     */
    public List<SlaveServerInfo> getSlaveServerInfos() {
        return slaveServerInfos;
    }

    /**
     * Setter method for property <tt>slaveServerInfos</tt>.
     * 
     * @param slaveServerInfos value to be assigned to property slaveServerInfos
     */
    public void setSlaveServerInfos(List<SlaveServerInfo> slaveServerInfos) {
        this.slaveServerInfos = slaveServerInfos;
    }

    /**
     * Getter method for property <tt>client</tt>.
     * 
     * @return property value of client
     */
    public RemoteClient getClient() {
        return client;
    }

    /**
     * Setter method for property <tt>client</tt>.
     * 
     * @param client value to be assigned to property client
     */
    public void setClient(RemoteClient client) {
        this.client = client;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
