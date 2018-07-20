package cn.zsza.redis;

import cn.zsza.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created By zhangsong
 * 22:01 2018/7/17
 */
public class RedisUtilTest extends BaseTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void testSetString(){
		redisUtil.setStr("name","zxc");
	}

	@Test
	public void getKeyByStr(){
		Assert.assertEquals("zxc",redisUtil.getKeyByStr("name"));
	}
}