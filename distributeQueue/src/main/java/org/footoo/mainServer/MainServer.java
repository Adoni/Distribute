/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

/**
 * 主服务器
 * 
 * @author fengjing.yfj
 * @version $Id: MainServer.java, v 0.1 2014年2月13日 下午6:21:35 fengjing.yfj Exp $
 */
public interface MainServer {

    /** 服务器正在运行 */
    public static final int RUNNING = 0;

    /** 服务器还没有启动 */
    public static final int INIT    = 1;

    /** 服务器挂起 */
    public static final int SUSPEND = 2;

    /** 服务器已经停止 */
    public static final int STOPPED = 3;

    /**
     * 启动主服务器
     * 
     */
    public void start();

    /**
     * 关闭主服务器
     * 
     */
    public void shutdown();

    /**
     * 暂停主服务器
     */
    public void suspend();

    /**
     * 恢复主服务器
     */
    public void resume();

    /**
     * 获取主服务器的状态
     * 
     * @return
     */
    public int getStatus();

}
