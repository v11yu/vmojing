package com.vmojing.crawler.parser;
import static org.junit.Assert.*;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import weibo4j.model.Status;

import com.vmojing.crawler.parser.convert.Converter;
import com.vmojing.crawler.parser.convert.WeiboConverter;
import com.vmojing.mongodb.domain.Weibo;

public class ConverterTest {
	Converter<Weibo, Status> c = new WeiboConverter();


	@Test
	public void testGetSuperClass() throws InstantiationException,
			IllegalAccessException {
		Converter<Weibo, Status> c = new WeiboConverter();
		ParameterizedType pt = (ParameterizedType) c.getClass()
				.getGenericSuperclass();
		System.out.println(pt);
		Type[] t = pt.getActualTypeArguments();
		Weibo w = (Weibo) ((Class) t[0]).newInstance();
		System.out.println(w);
		assertNotNull(w);
	}
	@Test
	public void testSetAuto() throws SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : c.getClass().getSuperclass().getDeclaredMethods()){
			System.out.println(m.getName());
			if(m.getName().equals("setAuto")){
				m.setAccessible(true);
				m.invoke(c, new Status(), new Weibo());
			}
		}
	}
	
}
