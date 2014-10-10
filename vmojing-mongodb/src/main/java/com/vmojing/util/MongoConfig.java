package com.vmojing.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * MongoDB配置文件
 * @author v11
 * @date 2014年9月30日
 */
public class MongoConfig {
	private MongoConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mongodb.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}
	public static int getNum(String key){
		return Integer.parseInt(props.getProperty(key));
	}
    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
