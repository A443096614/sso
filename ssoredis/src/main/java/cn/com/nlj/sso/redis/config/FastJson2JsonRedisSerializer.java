package cn.com.nlj.sso.redis.config;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;

/***
* 类说明：redis序列化
* @author nlj
* 2018年1月23日 下午11:59:21
*/
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	private Class<T> fistClass;
	
	public FastJson2JsonRedisSerializer(Class<T> fistClass){
        this.fistClass = fistClass;
    }
	
	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
            return new byte[0];
        }
		return JSON.toJSONString(t).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if(null == bytes || bytes.length <= 0){
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, fistClass);
	}

}
