package com.vmojing.crawler.work.interval;

public interface IntervalStrategy {

	/**
	 *  更新采集间隔
	 * @param interval 当前的采集间隔
	 * @param updateNumber 采集数
	 * @param updateLeftThreshold 采集数阈值左边
	 * @param updateRightThreshold 采集数阈值右边
	 * @param updateTime 增加或减少的单位时间长度
	 * @return
	 */
	Integer getInterval(Integer interval,Integer updateNumber,
			Integer updateLeftThreshold,Integer updateRightThreshold
			,Integer updateTime);
}
