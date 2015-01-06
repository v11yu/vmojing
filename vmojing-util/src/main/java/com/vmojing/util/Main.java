package com.vmojing.util;

import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.util.app.TagFetchApp;

public class Main {
	public static void main(String[] args) {

		TagFetchApp tagApp = (TagFetchApp) UtilSpringConfigSingleton.getBean("tagApp");
		tagApp.run();
	}
}
