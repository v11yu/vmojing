package com.vmojing.mongodb.utils;

import org.springframework.stereotype.Component;

import com.mongodb.DBObject;


public interface Converter<F,T> {

	T convertTo(F obj);
	F convertFrom(T obj);
}
