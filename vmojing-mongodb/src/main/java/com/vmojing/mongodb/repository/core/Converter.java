package com.vmojing.mongodb.repository.core;

import org.springframework.stereotype.Component;

import com.mongodb.DBObject;


public interface Converter<DB,POJO> {

	DB convertToDB(POJO obj);
	POJO convertToPojo(DB obj);
}
