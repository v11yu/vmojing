package com.vmojing.crawler.work.interval;

import com.vmojing.crawler.CrawlerConfig;

public class SimpleIntervalStrategy implements IntervalStrategy{
	private Integer minTime = CrawlerConfig.getNum("MinFrequentTime");
	private Integer maxTime = CrawlerConfig.getNum("MaxFrequentTime");


	@Override
	public Integer getInterval(Integer interval, Integer updateNumber,
			Integer updateLeftThreshold, Integer updateRightThreshold,
			Integer updateTime) {
		// TODO Auto-generated method stub
		if(updateNumber < updateLeftThreshold){
			interval = Math.min(interval+=updateTime, maxTime);
		}else if(updateNumber > updateRightThreshold){
			interval = Math.max(interval-=updateTime, minTime);
		}
		return interval;

	}

}
