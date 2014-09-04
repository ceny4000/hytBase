package com.hyt.base.utils;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyt.base.entity.Sample1;

/**
 * Title: JsonUtils 將字串轉出或轉入JSON格式
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/8/29
 */
public class JsonUtils {
	
    /**
     * Test
     * @param args
     */
    public static void main(String args[]) {
    	Sample1 sample1 = new Sample1();
    	sample1.setText1("rr");
    	sample1.setText2("cc");
    	
    	System.out.println(objToJson(sample1));
    	System.out.println(jsonToMap(objToJson(sample1)).get("text1"));
    }
	
	/**
	 * 將物件轉為JSON格式
	 * @param o
	 * @return
	 */
	public static String objToJson(Object o) {
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
	/**
	 * 將JSON轉為Map格式
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Gson gson = new Gson();  
		Map<String, Object> rtnMap = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
		return rtnMap;
	}
}
