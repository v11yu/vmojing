package com.vmojing.crawler.parser.convert;

import org.springframework.stereotype.Component;

import weibo4j.model.Status;

import com.vmojing.mongodb.domain.Weibo;
@Component
public class WeiboConverter extends Converter<Weibo, Status>{

	@Override
	public Weibo convert(Status from) {
		// TODO Auto-generated method stub
		Weibo res = new Weibo();
		return null;
	}

	@Override
	protected void setManualField(Status from, Weibo weibo) {
		// TODO Auto-generated method stub
		
	}



}
