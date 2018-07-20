package cn.zsza.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
/**
 * Created By zhangsong
 * 21:46 2018/7/17
 */
@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	public void setStr(String key,String value){
		stringRedisTemplate.opsForValue().set(key,value);
	}

	public String getKeyByStr(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}

}
