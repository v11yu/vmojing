package com.vmojing.mongodb.utils;

import java.net.UnknownHostException;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;
/**
 * <code>DB</code>单例模式
 * @author v11
 * @date 2014年9月30日
 */
public class MongoDbUtil {
	/** mongoDb server ip */
	private static String IP = MongoConfig.getValue("ip");
	/** mongoDb server port */
	private static Integer PORT = MongoConfig.getNum("port");
	/** 连接池大小 */
	private static Integer POOL_SIZE = MongoConfig.getNum("poolSize");
	/** 数据库名 */
	private static String DB_NAME = MongoConfig.getValue("dbName");
	private DB database;
	private static MongoDbUtil uniqueInstance;
	private static MongoClient mongo;
	private MongoDbUtil(){
		try {
			Builder builder = new MongoClientOptions.Builder();
            builder.autoConnectRetry(true);
            builder.connectionsPerHost(POOL_SIZE);
			mongo = new MongoClient(new ServerAddress(IP,PORT),builder.build() );
			database = mongo.getDB(DB_NAME);
		} catch (UnknownHostException ex) {
			throw new IllegalArgumentException(ex);
		} catch (MongoException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	/**
	 * Gets a collection with a given name.
	 * If the collection does not exist, a new collection is created.
	 * @param collectionName the name of the collection to return
	 * @return the collection
	 */
    public static synchronized DBCollection getCollection(String collectionName){
    	if(uniqueInstance == null){
    		uniqueInstance = new MongoDbUtil();
    	}
        return uniqueInstance.database.getCollection(collectionName);
    }
    /**
     * the mongoclient is singleton<p />
     * A MongoDB client with internal connection pooling. For most applications
     * @return
     */
    public static synchronized MongoClient getMongo(){
    	if(uniqueInstance == null){
    		uniqueInstance = new MongoDbUtil();
    	}
    	return uniqueInstance.mongo;
    }

}
