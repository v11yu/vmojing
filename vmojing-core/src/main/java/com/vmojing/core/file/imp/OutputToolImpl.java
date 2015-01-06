package com.vmojing.core.file.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.vmojing.core.file.OutputTool;

public class OutputToolImpl implements OutputTool{

	@Override
	public boolean newAndOutput(String filePath, String line) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean append(String filePath, String line) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean newAndOutput(String filePath, List<String> lines) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			for(String line:lines){
				out.write(line);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean append(String filePath, List<String> lines) {
		// TODO Auto-generated method stub
		return false;
	}

}
