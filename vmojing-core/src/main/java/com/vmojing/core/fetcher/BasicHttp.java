package com.vmojing.core.fetcher;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.jsoup.select.Elements;

public interface BasicHttp {
	/**
	 * 释放HttpResponse资源，用于在一次execute之后，释放资源
	 * @param res HttpResponse
	 */
	void release(HttpResponse res);
	/**
	 * 为一次HttpGet请求添加必要的报文头信息<p />
	 * 包括浏览器信息等
	 * @param url 请求的url
	 * @return HttpGet
	 */
	HttpGet addHttpGetWithHeader(String url);
	/**
	 * 为一次HttpPost请求添加必要的报文头信息<p />
	 * 包括浏览器信息等
	 * @param url 请求的url
	 * @return HttpPost
	 */
	HttpPost addHttpPostWithHeader(String url);
	/**
	 * 添加hidden信息
	 * @param formParams 字段列表
	 * @param inputs 名为<code>input</code>的elements
	 * @return formParams 字段列表
	 */
	List<NameValuePair> addHiddenInput(List<NameValuePair> formParams,Elements inputs);
	/**
	 * response的页面信息
	 * 打印一次，res就被清空了，为什么？
	 * 只能获取一次，因此获取后需保存
	 * @param res 页面请求Response
	 * @return 页面body信息
	 */
	String getResponseBody(HttpResponse res);
	/**
	 * response的报文头信息
	 * @param response
	 * @return 报文头信息
	 */
	String getHeaders(HttpResponse response);
	
}
