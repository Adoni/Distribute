/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.tools;

import java.io.File;

/**
 * 操作系统相关的工具
 * 
 * @author fengjing.yfj
 * @version $Id: SystemInfoTool.java, v 0.1 2014年2月13日 上午11:47:44 fengjing.yfj Exp $
 */
public abstract class SystemInfoTool {
    /**
     * 获取用户的目录
     * 
     * @return
     */
    public static final String getUserDir() {
        return System.getProperty("user.home");
    }

    /**
     * 获取完整的路径
     * 
     * @param dir 目录
     * @param path 路径
     * @return
     */
    public static final String getPath(String dir, String path) {
        return dir + File.separator + path;
    }

    /**
     * 获取相对于用户目录的绝对路径
     * 
     * @param path 相对于用户目录的路径
     * @return
     */
    public static final String getPathUnderUserDir(String path) {
        return getPath(getUserDir(), path);
    }

    /**
     * 创建目录,如果不存在的话
     * 
     * @param path
     * @throws DirectoryCannotCreateException 
     */
    public static final void createDirIfNotExist(String path) throws DirectoryCannotCreateException {
        File dir = new File(path);
        //路径已经存在，并且不是目录
        if (dir.exists() && !dir.isDirectory()) {
            throw new DirectoryCannotCreateException("已经存在与文件夹名[" + path + "]相同的文件，无法创建文件夹");
        }
        //创建目录
        if (!dir.mkdirs()) {
            throw new DirectoryCannotCreateException("无法创建文件夹[" + path + "],请检查权限.");
        }
    }

    /**
     * 在用户目录下创建目录,如果不存在的话
     * 
     * @param path 相对于用户目录的路径
     * @throws DirectoryCannotCreateException
     */
    public static final void createDirUnderUserDirIfNotExist(String path)
                                                                         throws DirectoryCannotCreateException {
        createDirIfNotExist(getPathUnderUserDir(path));
    }
}
