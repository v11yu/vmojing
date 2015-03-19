package com.vmojing.core.file;

import java.util.List;
import java.util.Map;

public interface InputTool {
	/**
	 * 将properties文件转化为map<p/>
	 * Example:
	 * <p/> name1=key1
	 * <p/>文件存放路径:classpath
	 * @param fileName 文件名
	 * @return map,失败：null
	 */
	Map<String,String> file2Map(String fileName);
	/**
	 * 将文件转化为List<String><p/>
	 * line -> String
	 * @param fileName 文件名
	 * @return List，失败：null
	 */
	List<String> file2List(String fileName);
}
