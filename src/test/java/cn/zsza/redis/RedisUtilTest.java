package cn.zsza.redis;

import cn.zsza.BaseTest;
import cn.zsza.Vo.U;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

/**
 * Created By zhangsong
 * 22:01 2018/7/17
 */
public class RedisUtilTest extends BaseTest {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private RedisTemplate redisTemplate;

	private static  ImmutableList<UserVo> userVoList;

	@Before
	public void initUserVoList(){
		userVoList = ImmutableList.of(new UserVo(11,"小A"),
				new UserVo(12,"小B"),
				new UserVo(13,"小D"),new UserVo(14,"小E"),new UserVo(15,"小F"));
	}
	@Test
	public void testSetString(){
		redisUtil.setStr("name","zxc");
	}
	@Test
	public void getKeyByStr(){
		Assert.assertEquals("zxc",redisUtil.getKeyByStr("name"));
	}
	/**
	 * redis存放List<Object>
	 */
	@Test
	public void testBoundHashOps(){
		userVoList.stream().forEach(userVo -> redisTemplate.boundHashOps("userVoList")
				.put(userVo.getUid(),userVo));
		/**
		 * 设置时间
		 */
//		redisTemplate.expire("userVoList",5,TimeUnit.SECONDS);

		Set<String> set = new TreeSet<>();

		set.stream().forEach( e -> redisTemplate.boundSetOps("aa").add(e));

	}
	@Test
	public void testBoundHashOpsGetUserVoList(){

		List<UserVo> userVoList = redisTemplate.boundHashOps("userVoList").values();
		System.out.println("userVoList:" + userVoList);


	}
	@Test
	public void testBoundHashOpsOpa(){
		UserVo userVo = new UserVo(16,"小g");
		BoundHashOperations userVoListOpa = redisTemplate.boundHashOps("userVoList");
		/**
		 * 在原来userVoList基础上，再添加一个（增）
		 */
		userVoListOpa.put(userVo.getUid(),userVo);
		/**
		 * 在原来userVoList基础上，删除一个（删）
		 */
//		System.out.println("res:" + userVoListOpa.delete(15));

		/**
		 * 查询（查询）
		 */
		UserVo voRes = (UserVo) userVoListOpa.get(11);
		System.out.println("voRes:" + voRes);
		System.out.println(userVoListOpa);
	}

	/**
	 * 修改（改）
	 */
	@Test
	public void testUpdateOne(){
		BoundHashOperations userVoListOpa = redisTemplate.boundHashOps("userVoList");
		UserVo vo = (UserVo) userVoListOpa.get(15);
		System.out.println("修改前的对象:" + vo);
		UserVo vo1 = new UserVo(15,"小FFF");
		userVoListOpa.put(vo1.getUid(),vo1);
		System.out.println("修改后的对象:" + userVoListOpa.get(15));
	}

	@Test
	public void increase() throws ExecutionException, InterruptedException {
//		System.out.println("result:" + 	redisUtil.getIncre("testInc"));


		final ExecutorService executor = Executors.newFixedThreadPool(5);

		Future<Long> r1 = executor.submit(() -> redisUtil.getIncre("6688"));
		Future<Long> r2 = executor.submit(() -> redisUtil.getIncre("6688"));
		Future<Long> r3 = executor.submit(() -> redisUtil.getIncre("6688"));

		System.out.println("current thread:" + Thread.currentThread().getName() + " result:" + r1.get());
		System.out.println("current thread:" + Thread.currentThread().getName() + " result:" + r2.get());
		System.out.println("current thread:" + Thread.currentThread().getName() + " result:" + r3.get());
		executor.shutdown();
	}

	@Test
	public void testIfAbsent(){
		Boolean flag = redisTemplate.opsForValue().setIfAbsent("name", "zsx");
		redisTemplate.expire("name",30,TimeUnit.SECONDS);

		System.out.println(flag);
	}


	@Test
	public void getObject(){
		U u = (U) redisTemplate.opsForValue().get("session:" + "C04E46FB92F3BE2FDC90AB9D3B0CE031");

		System.out.println(u);
	}



	@Test
	public void testEquals(){
		System.out.println(new UserVo(11,"小A").equals(new UserVo(11,"小A")));
	}


	@Data
	@AllArgsConstructor
	private static class UserVo implements Serializable {
		private static final long serialVersionUID = -2685829481271447334L;
		private Integer uid;
		private String name;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			UserVo userVo = (UserVo) o;
			return Objects.equal(uid, userVo.uid) &&
					Objects.equal(name, userVo.name);
		}


	}
}