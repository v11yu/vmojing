package com.vmojing.util.authorize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.vmojing.core.fetcher.api.TokenFetcher;
import com.vmojing.core.fetcher.impl.TokenFetcherImpl;
import com.vmojing.core.file.InputTool;
import com.vmojing.core.file.imp.InputToolImpl;
import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.mongodb.domain.AccessToken;
import com.vmojing.mongodb.repository.BasicRepository;

/**
 * 自动定期更新的token
 * 
 * @author v11
 */
@Component
public class AuthorizeApp {
	private static final Logger log = LoggerFactory.getLogger(AuthorizeApp.class);
	String userFile = "users.properties";
	String appFile = "app.properties";
	Map<String, String> user;
	List<String> appUrls;
	List<AccessToken> tokens;
	@Autowired
	@Qualifier("accessTokenDao")
	BasicRepository<AccessToken> accessTokenDao;

	TokenFetcher tf = new TokenFetcherImpl();
	void readUserInfo(){
		log.info("读入配置文件....");
		InputTool it = new InputToolImpl();
		user = it.file2Map(userFile);
		appUrls = it.file2List(appFile);
		log.info("用户数量:"+user.size());
		log.info("AppUrl数量:"+appUrls.size());
	}
	public void work(){
		log.info("开始自动更新token....");
		readUserInfo();
		Iterator<Entry<String, String>> iter = user.entrySet().iterator();
		tokens = new ArrayList<AccessToken>();
		while(iter.hasNext()){
			Entry<String, String> en = iter.next();
			for(String url:appUrls){
				String token = tf.getToken(en.getKey(), en.getValue(), url);
				int tryNum = 3;
				while(token == null && tryNum-->0){
					token = tf.getToken(en.getKey(), en.getValue(), url);
				}
				if(token != null){
					tokens.add(new AccessToken(token));
				}
			}
		}
		for(AccessToken t:tokens){
			log.info(""+t);
		}
		accessTokenDao.dropAll();
		accessTokenDao.saveAll(tokens);
		log.info("更新完成"+tokens.size()+"个....");
	}
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AuthorizeUtilRootConfiguration.class);

	}
}
