package com.xymiao.cms.service.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = getJsonRedisSerializer();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jsonRedisSerializer);
		// 支持事物
		// template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * 设置jackson的序列化方式
	 */
	private Jackson2JsonRedisSerializer<Object> getJsonRedisSerializer() {

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(om, Object.class);
		return redisSerializer;
	}

}