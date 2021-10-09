package com.xymiao.cms.service.util;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	public void set(String key, String value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value,timeout, unit);
	}
	
	public String get(String key) {
		return String.valueOf(redisTemplate.opsForValue().get(key));
	}
	
	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}
}
