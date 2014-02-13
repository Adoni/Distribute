/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.distributeQueue;

import org.footoo.common.exception.DistributeCommonException;

/**
 * 扩展分布式队列API
 * 主要管理每一个message的ID
 * 每一个存储的数据，即message都有一个唯一的ID标记这条数据
 * 这个ID是伴随着message的生命周期的，不像索引是随着增删改查不断改变
 * 
 * @author fengjing.yfj
 * @version $Id: ExtDistributeQueueAPI.java, v 0.1 2014年2月12日 下午8:12:47 fengjing.yfj Exp $
 */
public interface ExtDistributeQueueAPI {
    /**
     * 通过message的索引，获取message的ID
     * 
     * @param index 数据的索引
     * @return
     */
    public String getId(long index) throws OutofIndexException, DistributeCommonException;

    /**
     * 通过message的ID获取索引，
     * 在并发的情况下，只有启动了事务，返回的索引值才有意义的
     * 
     * @param id
     * @return
     * @throws NoSuchIdException
     * @throws DistributeCommonException
     */
    public long getIndexById(String id) throws NoSuchIdException, DistributeCommonException;

    /**
     * 插入并返回ID
     * 
     * @param msg 数据
     * @param index 插入位置索引
     * @return
     */
    public String insertAndReturnId(byte[] msg, long index) throws OutofIndexException,
                                                           DistributeCommonException;

    /**
     * 插入到队列的顶端，并且返回id
     * 
     * @param msg 数据
     * @return
     */
    public String pushTopAndReturnId(byte[] msg) throws DistributeCommonException;

    /**
     * 插入到队列的末尾，并且返回id
     * 
     * @param msg 数据
     * @return
     */
    public String pushTailAndReturnId(byte[] msg) throws DistributeCommonException;

    /**
     * 通过ID删除数据
     * 
     * @param id
     */
    public void deleteById(String id) throws NoSuchIdException, DistributeCommonException;

    /**
     * 通过message 的id删除并返回数据
     * 
     * @param id
     * @return
     */
    public byte[] deleteByIdAndReturn(String id) throws NoSuchIdException,
                                                DistributeCommonException;

}
