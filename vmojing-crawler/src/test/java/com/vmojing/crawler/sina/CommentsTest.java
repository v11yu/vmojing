package com.vmojing.crawler.sina;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.vmojing.crawler.fetcher.util.IdTransferUtil;
import com.vmojing.mongodb.business.AccessTokenAllocation;

import weibo4j.Comments;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

public class CommentsTest {
	Comments cm ;
	@Before
	public void initialize(){
		cm = new Comments();
		cm.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Test
	/**
	 * start 1 to 9
	 */
	public void testGetComments(){
		String mid = "BuzmOFn7z";
		String wid = IdTransferUtil.mid2Id(mid);
		Map<Long, Integer> mp = new HashMap<Long, Integer>();
		try {
			for(int i=1;i<11;i++){
				CommentWapper cw = cm.getCommentById(wid, 200, i);
				System.out.println(i+" "+cw.getComments().size());
				int pre = mp.size();
				for(Comment c : cw.getComments()){
					mp.put(c.getId(), 1);
				}
				if(pre == mp.size()){
					System.out.println("break ..."+i+"..size:"+mp.size());
					break;
				}
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("..size:"+mp.size());
	}
}
