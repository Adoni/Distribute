/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net;

import org.footoo.common.protocol.CommandPackage;

/**
 * 客户端接口
 * 
 * @author fengjing.yfj
 * @version $Id: RemoteClient.java, v 0.1 2014年2月12日 下午6:52:08 fengjing.yfj Exp $
 */
public interface RemoteClient {
    /**
     * 同步的方式调用请求
     * 
     * @param commandPackage 请求包
     * @param timeoutms 超时时间（ms)
     * @return 处理的结果的包
     */
    public CommandPackage invokeCommandSync(CommandPackage commandPackage, int timeoutms);
}
