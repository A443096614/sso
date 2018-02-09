package cn.com.nlj.sso.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/***
 * 类说明： Redis配置类
 * 
 * @author nlj 2018年1月6日 下午11:00:23
 */
@Configuration
@EnableCaching // 开启缓存注解
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.database}")
	private int database;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;
	
	@Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
       CacheManager cacheManager = new RedisCacheManager(redisTemplate);
       return cacheManager;
    }

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean
    public RedisTemplate<String, String> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
	}

	/**
	 * Jedis Redis连接的基础设置
	 * @return
	 */
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		// 存储的库
		factory.setDatabase(database);
		// 设置连接超时时间
		factory.setTimeout(timeout);
		factory.setUsePool(true);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	    jedisPoolConfig.setMaxIdle(maxIdle);
	    jedisPoolConfig.setMinIdle(minIdle);
		factory.setPoolConfig(jedisPoolConfig);
		return factory;
	}
}
