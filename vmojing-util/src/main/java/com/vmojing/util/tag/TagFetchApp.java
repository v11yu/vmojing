package com.vmojing.util.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.core.parser.api.TagParser;
import com.vmojing.mongodb.domain.Tag;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.utils.SpringConfigSingleton;
import com.vmojing.util.UtilSpringConfigSingleton;
import com.vmojing.util.app.SimpleApp;
@Component
@Scope("prototype")
/**
 * 标签采集获取的app
 * 
 * @author v11
 */
public class TagFetchApp extends SimpleApp{
	int curPage = 0;
	@Autowired
	@Qualifier("userDao")
	BasicRepository<User> userDao;
	@Autowired
	@Qualifier("tagDao")
	BasicRepository<Tag> tagDao;
	@Autowired
	TagParser tagParser;
	public TagFetchApp() {
		super(SimpleApp.DefaultDelayTime, 500);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void work() {
		Map<String, Integer> userIds = new HashMap<String, Integer>();
		// TODO Auto-generated method stub
		int pageSize = 400;
		List<User> seg = userDao.findByPageNum(pageSize*curPage, pageSize);
		splitUser(seg);
		curPage++;
		System.out.println("run time count:"+curPage);
	}
	private void splitUser(List<User> seg){
		String uids =null;
		int cnt = 0;
		for(User u:seg){
			if(uids == null){
				uids = u.getId();
				cnt=1;
			}
			else{
				uids = uids+","+u.getId();
				cnt++;
				if(cnt==20){
					save(uids);
					uids = null;
				}
			}
		}
		if(uids!=null) save(uids);
	}
	/**
	 * 
	 * @param uids 20条
	 */
	private void save(String uids){
		System.out.println("save");
		List<Tag> ts = tagParser.getTagsFromUsers(uids);
		if(ts == null) return ;
		for(Tag t:ts){
			System.out.println(t);
			try {
				tagDao.saveAndUpdate(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		TagFetchApp tagApp = (TagFetchApp) UtilSpringConfigSingleton.getBean("tagApp");
		tagApp.run();
	}
}
