package com.vmojing.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;
import com.vmojing.util.MongoConfig;
/**
 * <code>DB</code>单例模式
 * @author v11
 * @date 2014年9月30日
 */
public class MongoDbUtil {
	/** mongoDb服务器ip */
	private static String IP = MongoConfig.getValue("ip");
	/** mongoDb服务器端口 */
	private static Integer PORT = MongoConfig.getNum("port");
	/** 连接池大小 */
	private static Integer POOL_SIZE = MongoConfig.getNum("poolSize");
	/** 数据库名 */
	private static String DB_NAME = MongoConfig.getValue("dbName");
	private DB database;
	private static MongoDbUtil uniqueInstance;

	private MongoDbUtil(){
		try {
			Builder builder = new MongoClientOptions.Builder();
            builder.autoConnectRetry(true);
            builder.connectionsPerHost(POOL_SIZE);
			MongoClient mongo = new MongoClient(new ServerAddress(IP,PORT),builder.build() );
			database = mongo.getDB(DB_NAME);
		} catch (UnknownHostException ex) {
			throw new IllegalArgumentException(ex);
		} catch (MongoException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	/**
	 * 获取数据表连接
	 * @param collectionName 表名
	 * @return <code>DBCollection</code>
	 */
    public static synchronized DBCollection getCollection(String collectionName){
    	if(uniqueInstance == null){
    		uniqueInstance = new MongoDbUtil();
    	}
        return uniqueInstance.database.getCollection(collectionName);
    }

}
