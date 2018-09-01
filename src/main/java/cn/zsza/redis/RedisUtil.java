package cn.zsza.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
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

	public  boolean setExpire(String key, Long timeOut){
		return redisTemplate.expire(key,timeOut,TimeUnit.SECONDS);
	}
	/**
	 * 原子自增
	 * @param key
	 * @return
	 */
	public Long getIncre(String key){
		Long result = redisTemplate.opsForValue().increment(key, 1);
		return result;
	}

	public void test(){
	}

}
