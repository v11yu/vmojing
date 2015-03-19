package com.vmojing.crawler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.vmojing.crawler.client.CrawlerClient;
import com.vmojing.crawler.client.TopicInitClient;

public class Startup {
	/*
	 * scheduler
	 */

	@Autowired
	CrawlerClient mainClient;
	@Bean
	MethodInvokingJobDetailFactoryBean mainClientJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(mainClient);
		bean.setTargetMethod("start");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean mainClientTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = mainClientJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000);
		bean.setRepeatInterval(1000*60*10); // 1hour
		return bean;
	}
	@Autowired
	TopicInitClient initClient;
	@Bean
	MethodInvokingJobDetailFactoryBean initClientJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(initClient);
		bean.setTargetMethod("start");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean initClientTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = initClientJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000*60*10);
		bean.setRepeatInterval(1000*60*60); // 1hour
		return bean;
	}
	@Bean
	SchedulerFactoryBean scheduler(){
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		JobDetail job1 = mainClientJobDetail().getObject();
		Trigger trigger1 = mainClientTrigger().getObject();
		JobDetail job2 = initClientJobDetail().getObject();
		Trigger trigger2 = initClientTrigger().getObject();
		bean.setJobDetails(job1,job2);
		bean.setTriggers(trigger1,trigger2);
		return bean;
	}
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CrawlerRootConfiguration.class);

	}
}
