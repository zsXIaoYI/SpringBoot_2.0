package cn.zsza.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * Created By zhangsong
 * 13:59 2018/7/22
 */
@Configuration
public class RedisConfig {

	@Bean
	@Primary
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JsonRedisSerializer());
		return redisTemplate;
	}

	private class JsonRedisSerializer implements RedisSerializer<Object> {
		private final ObjectMapper om;

		private JsonRedisSerializer() {
			this.om = new ObjectMapper()
					.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		}
		@Override
		public byte[] serialize(Object o) throws SerializationException {
			try {
				return om.writeValueAsBytes(o);
			} catch (JsonProcessingException e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}
		@Override
		public Object deserialize(byte[] bytes) throws SerializationException {
			if(bytes == null){
				return null;
			}
			try {
				return om.readValue(bytes, Object.class);
			} catch (Exception e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}
	}
}
