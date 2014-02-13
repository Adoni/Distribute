/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.mainServer;

import org.footoo.common.exception.DistributeCommonException;

/**
 * 分布式队列的API
 * 
 * @author fengjing.yfj
 * @version $Id: DistributeQueueAPI.java, v 0.1 2014年2月12日 下午7:45:20 fengjing.yfj Exp $
 */
public interface DistributeQueueAPI {

    /**
     * 获取队列的长度
     * 
     * @return 队列的长度
     * @throws DistributeCommonException
     */
    public int size() throws DistributeCommonException;

    /**
     * 瞥一眼
     * 
     * @return
     * @throws OutofIndexException 越界异常
     * @throws DistributeCommonException 分布式异常
     */
    public byte[] peek() throws OutofIndexException, DistributeCommonException;

    /**
     * 删除并返回第一项
     * 
     * @return
     * @throws OutofIndexException
     * @throws DistributeCommonException
     */
    public byte[] pop() throws OutofIndexException, DistributeCommonException;

    /**
     * 删除并返回最后一项
     * 
     * @return
     * @throws OutofIndexException
     * @throws DistributeCommonException
     */
    public byte[] popTail() throws OutofIndexException, DistributeCommonException;

    /**
     * 将信息存放到队列的顶端
     * 
     * @param msg 存储的信息
     * @throws DistributeCommonException
     */
    public void pushTop(byte[] msg) throws DistributeCommonException;

    /**
     * 将信息存放到队列的末尾
     * 
     * @param msg 存放的信息
     * @throws DistributeCommonException
     */
    public void pushTail(byte[] msg) throws DistributeCommonException;

    /**
     * 插入到指定的索引位置
     * 
     * @param msg 存放的消息
     * @param index 索引
     * @throws OutofIndexException
     * @throws DistributeCommonException
     */
    public void insert(byte[] msg, long index) throws OutofIndexException,
                                              DistributeCommonException;

    /**
     * 删除索引出的消息
     * 
     * @param index
     * @throws OutofIndexException
     * @throws DistributeCommonException
     */
    public void delete(int index) throws OutofIndexException, DistributeCommonException;

    /**
     * 删除并且返回
     * 
     * @param index 索引
     * @return 原索引处的数据
     * @throws OutofIndexException
     * @throws DistributeCommonException
     */
    public byte[] deleteAndReturn(int index) throws OutofIndexException, DistributeCommonException;

}
