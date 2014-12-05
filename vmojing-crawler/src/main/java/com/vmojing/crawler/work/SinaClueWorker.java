package com.vmojing.crawler.work;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.Contants;
import com.vmojing.core.parser.api.CommentParser;
import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.crawler.queue.BasicQueue;

import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.business.api.ClueBusiness;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Weibo;

@Component
@Scope("prototype")
public class SinaClueWorker extends AbstractWorker<Clue>{
	
	@Autowired
	ClueBusiness clueBusiness;
	@Autowired
	WeiboParser weiboParser;
	@Autowired
	CommentParser commentParser;
	@Autowired
	public SinaClueWorker(BasicQueue<Clue> queue, 
			@Qualifier("noPush")PushStrategy pushStrategy) {
		super(queue, pushStrategy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void work(Clue t) {
		// TODO Auto-generated method stub
		Date begin = new Date();
		Date updateTime = t.getLastUpdateRetweetTime();
		getLogger().info("线索抓取线程开始："+t.getId());
		List<Weibo> weibos = weiboParser.getRetweet(t.getId(), t.getLastUpdateRetweetTime());
		List<Comment> comments = commentParser.getComment(t.getId(), t.getLastUpdateCommentTime());
		Integer updateWeiboNums = -1;
		Integer updateCommentNums = -1;
		//---retweet weibos doing
		if (weibos != null) {
			updateWeiboNums = weibos.size();
			for (Weibo w : weibos) {
				clueBusiness.saveRetweetWeibo(w, t);
				if (w.getCreatedAt().after(updateTime)) {
					updateTime = w.getCreatedAt();
				}
			}
			t.setLastUpdateRetweetTime(updateTime);
		}
		//---commet doing
		if (comments != null) {
			updateCommentNums = comments.size();
			updateTime = t.getLastUpdateCommentTime();
			for (Comment c : comments) {
				clueBusiness.saveComment(c, t);
				if (c.getCreatedAt().after(updateTime)) {
					updateTime = c.getCreatedAt();
				}
			}
			t.setLastUpdateCommentTime(updateTime);
		}
		
		clueBusiness.save(t);
		long useTime = new Date().getTime() - begin.getTime();
		getLogger().info("线索抓取线程结束："+t.getId()+",用时："+useTime / 1000 +
				"秒 ,评论更新个数："+updateCommentNums+" ,转发微博更新个数："+updateWeiboNums);
	}


}
