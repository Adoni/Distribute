/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common;

import org.footoo.common.tools.DirectoryCannotCreateException;
import org.footoo.common.tools.SystemInfoTool;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: SystemInfoTest.java, v 0.1 2014年2月13日 下午2:34:08 fengjing.yfj Exp $
 */
public class SystemInfoTest {
    public static void main(String args[]) throws DirectoryCannotCreateException {
        System.out.println(SystemInfoTool.getUserDir());
        System.out.println(SystemInfoTool.getPathUnderUserDir("he"));
        SystemInfoTool.createDirUnderUserDirIfNotExist("hello\\hehe\\hh");
    }
}
