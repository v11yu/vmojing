package com.vmojing.crawler.work.check;
/**
 * no check,just return true
 * @author v11
 * @date 2014年12月9日
 * @version 1.0
 */
public class NoChecker implements CheckStrategy{

	@Override
	public <T> boolean check(T t) {
		// TODO Auto-generated method stub
		return true;
	}

}
