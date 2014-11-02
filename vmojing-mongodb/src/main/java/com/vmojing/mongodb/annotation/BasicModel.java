package com.vmojing.mongodb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 采集程序最基本的数据结构<p />
 * Sina微博而言
 * <ol>
 * <li>weibo</li>
 * <li>user</li>
 * <li>comment</li>
 * </ol>
 * @author v11
 * @date 2014年10月29日
 * @version 1.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BasicModel {
	String value() default "";
}

