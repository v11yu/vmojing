package com.vmojing.core.file;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

import com.vmojing.core.file.imp.OutputToolImpl;

public class OutputToolTest {
	@Test
	public void testWrite(){
		OutputTool ot = new OutputToolImpl();
		List<String> ls = new ArrayList<String>();
		ls.add("hello");
		ls.add("world");
		String file = "d://file.txt";
		assertTrue(ot.newAndOutput(file, ls));
	}
}
