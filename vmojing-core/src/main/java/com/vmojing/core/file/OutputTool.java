package com.vmojing.core.file;

import java.util.List;

public interface OutputTool {
	boolean newAndOutput(String filePath,String line);
	boolean append(String filePath,String line);
	boolean newAndOutput(String filePath,List<String> lines);
	boolean append(String filePath,List<String> lines); 
}
