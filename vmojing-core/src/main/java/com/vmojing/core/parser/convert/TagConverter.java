package com.vmojing.core.parser.convert;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Tag;

@Component
@Scope("prototype")
public class TagConverter extends Converter<Tag, weibo4j.model.Tag>{

	@Override
	protected void setManualField(weibo4j.model.Tag from, Tag to) {
		// TODO Auto-generated method stub
		
	}

}
