package com.vmojing.core.parser;

import weibo4j.model.WeiboException;

/**
 * 错误码处理：<a href="http://open.weibo.com/wiki/Error_code">详情</a>
 * @author v11
 * @date 2014年11月3日
 * @version 1.0
 */
public class WeiboExceptionHandle {
	public static String getErrorString(WeiboException e,String msg){
		return msg+" errorCode："+e.getErrorCode()+",statusCode:"+e.getStatusCode()+" ,"+e;
	}
}
