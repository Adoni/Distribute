/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.net.netty;

import java.util.concurrent.CountDownLatch;

import org.footoo.common.net.CommandInvokedCallback;
import org.footoo.common.net.SendedCallback;
import org.footoo.common.protocol.CommandPackage;
import org.jboss.netty.channel.ChannelFuture;

/**
 * 待发送的package的信息
 * 
 * @author fengjing.yfj
 * @version $Id: SendingPackageInfo.java, v 0.1 2014年2月14日 下午2:28:17 fengjing.yfj Exp $
 */
public class SendingPackageInfo {
    /** 发送的报文 */
    private CommandPackage         commandPackage;
    /** 发送的期待结果 */
    private ChannelFuture          channelFuture;
    /** 发送完成的回调函数 */
    private SendedCallback         sendedCallback;
    /** 接受到响应的回调函数 */
    private CommandInvokedCallback commandInvokedCallback;
    /** 是否需要等待响应报文 */
    private boolean                oneWay;
    /** 同步工具 */
    private CountDownLatch         countDownLatch;

    /**
     * Getter method for property <tt>commandPackage</tt>.
     * 
     * @return property value of commandPackage
     */
    public CommandPackage getCommandPackage() {
        return commandPackage;
    }

    /**
     * Setter method for property <tt>commandPackage</tt>.
     * 
     * @param commandPackage value to be assigned to property commandPackage
     */
    public void setCommandPackage(CommandPackage commandPackage) {
        this.commandPackage = commandPackage;
    }

    /**
     * Getter method for property <tt>channelFuture</tt>.
     * 
     * @return property value of channelFuture
     */
    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    /**
     * Setter method for property <tt>channelFuture</tt>.
     * 
     * @param channelFuture value to be assigned to property channelFuture
     */
    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    /**
     * Getter method for property <tt>sendedCallback</tt>.
     * 
     * @return property value of sendedCallback
     */
    public SendedCallback getSendedCallback() {
        return sendedCallback;
    }

    /**
     * Setter method for property <tt>sendedCallback</tt>.
     * 
     * @param sendedCallback value to be assigned to property sendedCallback
     */
    public void setSendedCallback(SendedCallback sendedCallback) {
        this.sendedCallback = sendedCallback;
    }

    /**
     * Getter method for property <tt>commandInvokedCallback</tt>.
     * 
     * @return property value of commandInvokedCallback
     */
    public CommandInvokedCallback getCommandInvokedCallback() {
        return commandInvokedCallback;
    }

    /**
     * Setter method for property <tt>commandInvokedCallback</tt>.
     * 
     * @param commandInvokedCallback value to be assigned to property commandInvokedCallback
     */
    public void setCommandInvokedCallback(CommandInvokedCallback commandInvokedCallback) {
        this.commandInvokedCallback = commandInvokedCallback;
    }

    /**
     * Getter method for property <tt>oneWay</tt>.
     * 
     * @return property value of oneWay
     */
    public boolean isOneWay() {
        return oneWay;
    }

    /**
     * Setter method for property <tt>oneWay</tt>.
     * 
     * @param oneWay value to be assigned to property oneWay
     */
    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    /**
     * Getter method for property <tt>countDownLatch</tt>.
     * 
     * @return property value of countDownLatch
     */
    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    /**
     * Setter method for property <tt>countDownLatch</tt>.
     * 
     * @param countDownLatch value to be assigned to property countDownLatch
     */
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

}
