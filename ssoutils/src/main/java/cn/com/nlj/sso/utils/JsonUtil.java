package cn.com.nlj.sso.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.nlj.sso.exception.BusinessException;

/**
 * @author nlj 2017年9月25日 上午10:41:58
 *
 * 类说明：json解析
 */
public class JsonUtil {
	
	public static String toJSONString(Object object) {
		try {
			return JSON.toJSONString(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("对象转json时出现异常");
		}
	}
	
	public static JSONArray parseArray(JSONObject jsonObject, String key, boolean isRetNull) {

		try {
			Object objStr = jsonObject.get(key);
			if (objStr == null && isRetNull == true) {
				return null;
			} else if (objStr == null) {
				throw new BusinessException(String.format("json中key：%s不存在，请检查传入的json字符串", key));
			}
			return JSON.parseArray(jsonObject.get(key).toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(String.format("解析报文失败，请检查json中key：%s对应的值", key));
		}
	}
	
	public static JSONObject parseObject(String objJsonStr) {
		if (StringUtil.isEmpty(objJsonStr)) {
			throw new BusinessException("传入的json为null或空字符串，请检查传入的数据");
		}
		try {
			return JSON.parseObject(objJsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("解析报文失败，请检查传入的报文");
		}
	}

	public static <T> T toBean(String objJsonStr, Class<T> objClass) {
		if (StringUtil.isEmpty(objJsonStr)) {
			throw new BusinessException("传入的json为null或空字符串，请检查传入的数据");
		}
		
		try {
			return JSON.parseObject(objJsonStr, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("解析报文失败，请检查传入的报文");
		}
	}
	
	public static <T> T toBean(String objJsonStr, String key, Class<T> objClass) {
		try {
			JSONObject jsonObject = parseObject(objJsonStr);
			return toBean(key, objClass, jsonObject, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(String.format("解析报文失败，请检查json中key：%s对应的值", key));
		}
	}
	
	public static <T> T toBean(String objJsonStr, String key, Class<T> objClass, boolean isRetNull) {
		try {
			JSONObject jsonObject = parseObject(objJsonStr);
			return toBean(key, objClass, jsonObject, isRetNull);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(String.format("解析报文失败，请检查json中key：%s对应的值", key));
		}
	}

	/***
	 * json转对象
	 * @param key json中的某个key,key对应的value为对象或数组对象
	 * @param objClass 对象类
	 * @param jsonObject json对象
	 * @param isRetNull 是否允许返回null(true：允许返回null，false：不允许)
	 * @return <T> T
	 */
	public static <T> T toBean(String key, Class<T> objClass, JSONObject jsonObject, boolean isRetNull) {
		Object objStr = jsonObject.get(key);
		if (objStr == null && isRetNull == true) {
			return null;
		} else if(objStr == null) {
			throw new BusinessException(String.format("json中key：%s不存在，请检查传入的json字符串", key));
		}
		return toBean(objStr.toString(), objClass);
	}
	
	public static <T> List<T> toList(String arrayJsonStr, Class<T> objClass) {
		if (StringUtil.isEmpty(arrayJsonStr)) {
			throw new BusinessException("传入的json为null或空字符串，请检查传入的数据");
		}
		try {
			return JSON.parseArray(arrayJsonStr, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("解析报文失败，请检查传入的报文");
		}
	}
	
	public static <T> List<T> toList(String objJsonStr, String key, Class<T> objClass) {
		try {
			JSONObject jsonObject = parseObject(objJsonStr);
			if (jsonObject == null) {
				return null;
			}
			Object objStr = jsonObject.get(key);
			return toList(objStr.toString(), objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(String.format("解析报文失败，请检查json中key：%s对应的值", key));
		}
	}
	
	/***
	 * json字符串转List
	 * @param key json中的某个key,key对应的value为对象或数组对象
	 * @param objClass 
	 * @param jsonObject json对象
	 * @param isRetNull 是否允许返回null(true：允许返回null，false：不允许)
	 * @return <T> List<T>
	 */
	public static <T> List<T> toList(String key, Class<T> objClass, JSONObject jsonObject, boolean isRetNull) {
		Object objStr = jsonObject.get(key);
		if (objStr == null && isRetNull == true) {
			return null;
		} else if(objStr == null) {
			throw new BusinessException(String.format("解析报文失败，请检查json中key：%s对应的值", key));
		}
		return toList(objStr.toString(), objClass);
	}

	/***
	 * json格式拼接
	 * @param map map
	 * @param jsonStr 需要拼接的json串
	 * @return 拼接好的json
	 */
	public static String jsonDataJoin(Map<String, Object> map, String jsonStr) {
		StringBuilder sb = new StringBuilder(256);
		sb.append(JsonUtil.toJSONString(map));
		sb.deleteCharAt(sb.length()-1);
		sb.append(",");
		sb.append(jsonStr.substring(1, jsonStr.length()));
		return sb.toString();
	}
}
