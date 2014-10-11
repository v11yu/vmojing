package com.vmojing.dao.utils;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.DBCursor;
import com.vmojing.dao.impl.BasicDAO;
/**
 * 新浪微博AccessToken单例
 * @author v11
 * @date 2014年10月11日
 * @Since 1.0
 */
public class AccessTokenAllocation{
	/** 默认表单名 */
	private static final String DEFAULT_COLLECTION_NAME = "access_token";
	private static final Logger log = LoggerFactory.getLogger(AccessTokenAllocation.class);
	/** singleton*/
	private static AccessTokenAllocation uniqueAccessTokenAllocation;
	/** DAO*/
	private BasicDAO basicDao;
	private List<String> accessTokens;
	private Integer accessTokenIndex;
	private AccessTokenAllocation(){
		basicDao = new BasicDAO(DEFAULT_COLLECTION_NAME);
		accessTokenIndex = 0;
		accessTokens = new ArrayList<String>();
		DBCursor cursor = basicDao.findByAll();
		try{
			while(cursor.hasNext()){
				String accessToken =  (String)cursor.next().get("access_token");
				accessTokens.add(accessToken);
			}
		}catch(Exception e){
			log.error("获取accessToken 异常！");
		}finally{
			cursor.close();
		}
	}
	/**
	 * 获取AccessToken
	 * @return AccessToken
	 */
	public synchronized static String getAccessToken(){
		if(uniqueAccessTokenAllocation == null){
			uniqueAccessTokenAllocation = new AccessTokenAllocation();
		}
		uniqueAccessTokenAllocation.accessTokenIndex %= uniqueAccessTokenAllocation.accessTokens.size();
		return uniqueAccessTokenAllocation.accessTokens.get(uniqueAccessTokenAllocation.accessTokenIndex++);
	}
	/**
	 * 系统AccessToken过期<br />
	 * 重新获取AccessToken,并载入AccessToken
	 */
	public synchronized static void restartAccessToken(){
		// setNewAccessToken
		if(uniqueAccessTokenAllocation != null)
			uniqueAccessTokenAllocation.setNewAccessToken();
		uniqueAccessTokenAllocation = new AccessTokenAllocation();
	}
	/**
	 * 重新获取AccessToken
	 * <待完成>
	 */
	private void setNewAccessToken(){
		
	}
	


}
