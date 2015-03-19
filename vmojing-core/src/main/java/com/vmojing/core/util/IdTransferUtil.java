package com.vmojing.core.util;

public class IdTransferUtil {
	private static String[] str62keys = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };

	/**
	 *  62进制转10进制
	 * @param str62
	 * @return
	 */
	public static long str62To10(String str62) {
		long i10 = 0;
		String[] array = strToArray(str62);
		for (int i = 0; i < array.length; i++) {
			long n = array.length - i - 1;
			String s = array[i];
			i10 += getIndex(s) * Math.pow(62, n);
		}
		return i10;
	}

	/**
	 *  将string变为数组
	 * @param str
	 * @return
	 */
	public static String[] strToArray(String str) {
		String[] s = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			s[i] = str.substring(i, i + 1);
		}
		return s;
	}

	/**
	 *  字母在str62keys中 出现的位置
	 * @param s
	 * @return
	 */
	public static int getIndex(String s) {
		int t = 0;
		for (int i = 0; i < str62keys.length; i++) {
			if (s.equals(str62keys[i])) {
				t = i;
			} 
		}
		return t;
	}

	/**
	 * 通过mid获取id
	 * @param mid
	 * @return
	 */
	public static String mid2Id(String mid) {
		String id = "";
		// 从最后往前以4字节为一组读取URL字符
		for (int i = mid.length() - 4; i > -4; i = i - 4) {
			int offset1 = i < 0 ? 0 : i;
			int offset2 = i + 4;
			String str = mid.substring(offset1, offset2);
			str = String.valueOf(str62To10(str));
			// 若不是第一组，则不足7位补0
			if (offset1 > 0) {
				while (str.length() < 7) {
					str = "0" + str;
				}
			}
			id = str + id;
		}
		return id;
	}
	
	/**
	 * 将id转化为mid
	 * @param id
	 * @return
	 */
	public static String id2Mid(String id){
        id = id.trim();   
        String mid = "";     
        for (int i = id.length() - 7; i > -7; i = i - 7) //从最后往前以7字节为一组读取字符     
        {     
            int offset = i < 0 ? 0 : i;     
            int len = i < 0 ? id.length() % 7 : 7;     
            String str = TentoN(Long.parseLong(id.substring(offset, offset+len)),62);     
            mid = str + mid;     
        }     
        return mid;     
    }    
	
	/**
	 * TentoN
	 * @param value
	 * @param number 转化的进制(这里是你想转换的数 ,这里是你想转换为多少进制 2-62之间）     
	 * @return
	 */
    public static String TentoN(long value, int number) {     
        if (number <= 1 || number > str62keys.length) {     
            throw new RuntimeException("Faild");     
        }     
        //负数处理     
        if (value < 0) {     
            return "-" + TentoN(0 - value, number);     
        }     
        if (value < number) {     
            return str62keys[(int)value];     
        } else {     
            long n = value % (long)number;     
            return (TentoN(value / number, number) + str62keys[(int)n]);     
        }     
    }     
    /**
     * tranfer url to mid
     * @param url 微博链接
     * @return mid,or null if fail
     */
    public static String url2Mid(String url){
    	try{
    		String ls[] = url.split("[?]")[0].split("/");
    		return ls[ls.length-1];
    	}catch (Exception e) {
			// TODO: handle exception
    		return null;
		}
    }
	public static void main(String[] args) {
		System.out.println(id2Mid("3474920895989800"));
		System.out.println(mid2Id("yvr29p8dG"));
	}
}