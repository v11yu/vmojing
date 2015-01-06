package com.vmojing.core.parser;

import org.apache.commons.lang3.exception.ExceptionUtils;

import weibo4j.model.WeiboException;

/**
 * 错误码处理：<a href="http://open.weibo.com/wiki/Error_code">详情</a>
 * @author v11
 * @date 2014年11月3日
 * @version 1.0
 */
public class WeiboExceptionHandle {
	/**
	 * 错误处理
	 * @param e 错误
	 * @param msg 前缀信息
	 * @return
	 */
	public static String getErrorString(WeiboException e,String msg){
		return msg+" errorCode："+e.getErrorCode()+",statusCode:"+e.getStatusCode()+" ,"+e+ExceptionUtils.getStackTrace(e);
	}
	public static String getErrorString(WeiboException e){
		return " errorCode："+e.getErrorCode()+",statusCode:"+e.getStatusCode()+" ,"+e+ExceptionUtils.getStackTrace(e);
	}
}
