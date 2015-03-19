package com.vmojing.core.file.imp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.core.file.InputTool;

public class InputToolImpl implements InputTool{
	private static final Logger log = LoggerFactory.getLogger(InputToolImpl.class);
	@Override
	public Map<String, String> file2Map(String fileName) {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Entry<Object, Object>> configs = properties.entrySet();
		Iterator<Entry<Object, Object>> iterator = configs.iterator();
		while (iterator.hasNext()) {
			Entry<Object, Object> config = iterator.next();
			String userName = (String) config.getKey();
			String pwd = (String) config.getValue();
			map.put(userName, pwd);
		}
		return map;
	}

	@Override
	public List<String> file2List(String fileName) {
		// TODO Auto-generated method stub
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		List<String> res = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String lineString = null;
			while((lineString = reader.readLine())!=null ){
				res.add(lineString);
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(ExceptionUtils.getStackTrace(e));
		} 

		return null;
	}

}
