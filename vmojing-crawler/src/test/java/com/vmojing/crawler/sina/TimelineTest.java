package com.vmojing.crawler.sina;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.vmojing.core.util.IdTransferUtil;
import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.mongodb.business.AccessTokenAllocation;

import static org.junit.Assert.*;
/**
 * TimeLine测试
 * 注意：
 * <ol>
 * <li>一.网页抓取，话题仅能获取最新1000个</li>
 * <li>二.利用接口获取指定用户发微博情况，仅能获取最新200</li>
 * <li>三.利用接口获取单条微博最新转发，最多2000条</li>
 * </lo>
 * @author v11
 * @date 2014年11月2日
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CrawlerRootConfiguration.class})
public class TimelineTest {
	private static Logger log = LoggerFactory.getLogger(TimelineTest.class);

	@Test
	public void testUserWeiboPage(){
		Timeline tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
		try {
			for(int i=1;i<10;i++){
				StatusWapper sw = tm.getUserTimelineBatchByUids("2144684673",200,i);
				assertNotNull(sw);
				assertNotNull(sw.getStatuses());
				log.info(i+" "+sw.getStatuses().size());
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			System.out.println(""+e);
		}
	}
	@Test
	/**最多2000条 ,页码从1开始*/
	public void testRetweet(){
		String mid = "BupvaEcDV";
		Timeline tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
		String wid = IdTransferUtil.mid2Id(mid);
		Map<String, Integer> mm = new HashMap<String, Integer>();
		try {
			for(int i=1;i<11;i++){
				int pre = mm.size();
				StatusWapper sw = tm.getRepostTimeline(wid,200,i);
				assertNotNull(sw);
				assertNotNull(sw.getStatuses());
				for(Status st :sw.getStatuses()){
					mm.put(st.getId(), 1);
				}
				if(pre == mm.size()){
					System.out.println("break...."+i);
					break;
				}
				log.info(i+" "+sw.getStatuses().size());
			}

		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
