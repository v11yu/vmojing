package com.vmojing.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vmojing.mongodb.MongoBeansConfiguration;
import com.vmojing.mongodb.MongoConfiguration;
import com.vmojing.util.app.TagFetchApp;

@Configuration

@Import({ MongoConfiguration.class ,MongoBeansConfiguration.class})
@ComponentScan({"com.vmojing.mongodb","com.vmojing.core","com.vmojing.util"})
public class UtilRootConfiguration {

}
