package cn.com.nlj.sso.redis.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/***
* 类说明：
* @author nlj
* 2018年1月6日 下午11:08:48
*/
public abstract class RedisClient {

	@Autowired
    protected RedisTemplate<String, Object> redisTemplate;
	
	protected HashOperations<String, String, Object> hashOperations() {
		HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
		return opsForHash;
	}
	
	protected ValueOperations<String, Object> valueOperations() {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		return opsForValue;
	}

	/***
	 * 往Hash表里添加元素
	 *  hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象
	 * @param key hash表
	 * @param field 键
	 * @param Obj value值
	 * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
	 */
    public void hmset(String key, String field, Object Obj, long expire) {
        hashOperations().put(key, field, Obj);
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /***
     * 删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     * @param key 要删除的hash表
     * @param field 键
     */
    public void hdel(String key, Object ...field) {
        hashOperations().delete(key, field);
    }

    /***
     * 返回哈希表中指定字段的值
     * @param key hash表
     * @param field 键
     * @return
     */
    public Object hget(String key, String field) {
        return hashOperations().get(key, field);
    }

    /***
     * 返回哈希表中，所有的字段和值, 若 key 不存在，返回空列表
     * @return
     */
    public List<Object> hgetall(String key) {
        return hashOperations().values(key);
    }

    /***
     * 获取当前redis库下所有key
     * @return
     */
    public Set<String> getKeys(String key) {
        return hashOperations().keys(key);
    }

    /***
     * 判断key是否存在redis中
     * @param key
     * @param field
     * @return
     */
    public boolean isKeyExists(String key, String field) {
        return hashOperations().hasKey(key, key);
    }

    /***
     * 查询当前key下缓存数量
     * @param key
     * @return
     */
    public long count(String key) {
        return hashOperations().size(key);
    }

    /**
     * 清空redis
     */
    public void empty(String key) {
        Set<String> set = hashOperations().keys(key);
        set.stream().forEach(k -> hashOperations().delete(key, k));
    }
    
    /***
     * 操作单个元素
     * @param key
     * @param value
     */
    public void set(String key, Object value){  
    	valueOperations().set(key, value);  
    }  
    
    /***
     * 获取单个元素
     * @param key
     * @return
     */
    public Object get(String key){  
        return valueOperations().get(key);  
    }
}
