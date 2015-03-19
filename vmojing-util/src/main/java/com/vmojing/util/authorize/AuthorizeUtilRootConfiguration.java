package com.vmojing.util.authorize;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.vmojing.crawler.CrawlerBeansConfiguration;
import com.vmojing.mongodb.MongoBeansConfiguration;
import com.vmojing.mongodb.MongoConfiguration;
import com.vmojing.util.tag.TagFetchApp;

//@Configuration

@Import({ MongoConfiguration.class ,MongoBeansConfiguration.class})
@ComponentScan({"com.vmojing.mongodb","com.vmojing.core","com.vmojing.util"})
public class AuthorizeUtilRootConfiguration {
	@Autowired
	AuthorizeApp app;
	@Bean
	MethodInvokingJobDetailFactoryBean aotuUpdateTokenJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(app);
		bean.setTargetMethod("work");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean aotuUpdateTokenTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = aotuUpdateTokenJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000);
		bean.setRepeatInterval(1000*60*60*24); // 1day
		return bean;
	}
	@Bean
	SchedulerFactoryBean scheduler(){
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		JobDetail job2 = aotuUpdateTokenJobDetail().getObject();
		Trigger trigger2 = aotuUpdateTokenTrigger().getObject();
		bean.setJobDetails(job2);
		bean.setTriggers(trigger2);
		return bean;
	}
}
