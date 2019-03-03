package com.zy.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class JsonTool {

	/**
	 * 获取JSON对象
	 * @param jsonStr
	 * @return
	 */
	public static JSONObject getJSON(String jsonStr){
		if(jsonStr==null || jsonStr.equals("")) return null;
		try{
			JSONObject jb = JSONObject.fromObject(jsonStr);
			return jb;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 把json转换成map（只有能转换第一级）
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,String> converJsonToMap(String jsonStr){
		if(jsonStr==null || jsonStr.equals("")) return null;
		Map map = new HashMap();
		JSONObject jb = getJSON(jsonStr);
		converJsonToMap(jb);
		return map;
	}

	/**
	 * 把json转换成List
	 * @param jsonStr
	 * @return
	 */
	public static List converJsonToList(String jsonStr){
		if(jsonStr==null || jsonStr.equals("")) return null;
		List list = new ArrayList();
		JSONArray ja = getJsonArray(jsonStr);
		list = JSONArray.toList(ja);;
		return list;
	}



	/**
	 * 把json转换成map（只有能转换第一级）
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,String> converJsonToMap(JSONObject jb){
		if(jb==null || jb.isEmpty()) return null;
		Map map = new HashMap();
		for (Iterator iterator2 = jb.keySet().iterator(); iterator2.hasNext();) {
			String key = iterator2.next().toString(); //拿到字段的名字
			String val = (jb.get(key)==null || jb.get(key).toString().equals("") || jb.get(key).toString().equals("null")) ? "0":jb.get(key).toString();
			map.put(key, val);
		}
		return map;
	}


	/**
	 * 把字符串转换成json数组
	 * @param jsonStr
	 * @return
	 */
	public static JSONArray getJsonArray(String jsonStr){
		if(jsonStr==null || jsonStr.equals("")) return null;
		try{
			JSONArray ja = JSONArray.fromObject(jsonStr);
			return ja;
		}catch(Exception e){
			System.out.println("json解释出错:"+jsonStr);
			return null;
		}
	}
}
