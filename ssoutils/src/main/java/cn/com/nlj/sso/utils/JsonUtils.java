package cn.com.nlj.sso.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/***
* ��˵����
* @author nlj
* 2017��12��10�� ����10:22:56
*/
public class JsonUtils {

	public static JSONObject parseObject(String text) {
		return JSON.parseObject(text);
	}
	
	public static String toJSONString(Object obj) {
		return JSON.toJSONString(obj);
	}
}
