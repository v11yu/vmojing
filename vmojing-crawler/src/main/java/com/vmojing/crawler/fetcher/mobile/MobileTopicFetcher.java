package com.vmojing.crawler.fetcher.mobile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vmojing.crawler.fetcher.BasicFetcher;
import com.vmojing.crawler.fetcher.IdTransferUtil;
import com.vmojing.crawler.fetcher.Loginer;
import com.vmojing.crawler.fetcher.TopicFetcher;



/**
 * 话题微博ids获取器
 * @author v11
 * @date 2014年9月5日
 * @Since 1.0
 */
public class MobileTopicFetcher extends BasicFetcher implements TopicFetcher{
	private final static Integer MaxPageNum = 100;
	private final Logger log = Logger.getLogger(this.getClass().getName());
	private HttpClient client;
	/**
	 * 
	 * @param loginClient 登陆后的Client
	 */
	public MobileTopicFetcher(HttpClient loginClient){
		client = loginClient;
	}
	
	@Override
	public Set<String> getIds(String words){
		return getIds(words,100,"","");
	}
	@Override
	public Set<String> getIds(String words,Integer pageNum,String starttime,String endtime){
		Set<String> wids = new HashSet<String>();
		
		String names[] = words.split(",");

		for (String word : names) {
			Set<String> ids = new HashSet<String>();
			String wordUrlEncode = null;
			try {
				wordUrlEncode = URLEncoder.encode(word, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String Url = "http://weibo.cn/search/mblog?hideSearchFrame=&keyword="
					+ wordUrlEncode
					+ "&advancedfilter=1&hasori=1&starttime="
					+ starttime + "endtime=" + endtime + "&sort=time&page=";
			String searchUrl;
			int count = 0;
			Document doc = null;
			if (pageNum > MaxPageNum)
				pageNum = MaxPageNum;
			for (int i = 1; i <= pageNum; i++) {
				try {
					searchUrl = Url + i;
					log.debug(searchUrl);
					HttpGet getSearch = addHttpGetWithHeader(searchUrl);
					HttpResponse res;
					res = client.execute(getSearch);
					doc = Jsoup.parse(toStringResponseBody(res));
					release(res);
					Elements es = doc.getElementsByTag("div");
					for (Element e : es) {
						String idName = e.attr("id");
						if (idName.startsWith("M_")) {
							count++;
							String mid = idName
									.substring(idName.indexOf("_") + 1);
							String id = IdTransferUtil.mid2Id(mid);
							ids.add(id);
						}
					}
				} catch (ClientProtocolException e) {
					log.error(e.getMessage());
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
			log.info(word + "关键词获得的搜索结果数为：" + count);
		}
		return wids;
	}
	
	public static void main(String[] args) {
		Loginer loginer = new MobileSinaLoginer();
		if (loginer.login()) {
			TopicFetcher t = new MobileTopicFetcher(loginer.getClient());
			t.getIds("林治波");
		}
	}
}
