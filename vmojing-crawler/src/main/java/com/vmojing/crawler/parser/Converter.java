package com.vmojing.crawler.parser;

public interface Converter <T,F>{
	T convert(F from);
}
