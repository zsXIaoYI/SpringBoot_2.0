package cn.zsza.controller;

import cn.zsza.redis.RedisUtil;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;
import java.util.concurrent.*;

/**
 * Created By zhangsong
 * 17:41 2018/7/22
 */
@RequestMapping("/redis")
@Controller
public class RedisController {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	private static final ImmutableList<Obj> objList = ImmutableList.of(new Obj(1,20),new Obj(2,30));



	@RequestMapping("/add")
	@ResponseBody
	public String testRedis(){
		objList.stream().forEach(obj -> redisTemplate.boundHashOps("objList").put(obj.getId(),obj));
		return "success";
	}

	@RequestMapping("/upe")
	@ResponseBody
	public String testUpdate(Integer id) throws InterruptedException {


		Obj obj = (Obj) redisTemplate.boundHashOps("objList").get(id);
		obj.setCount(obj.getCount() - 1);

		Thread.sleep(5000);
		redisTemplate.boundHashOps("objList").put(id,obj);
		return "success";
	}

	@RequestMapping("/get")
	@ResponseBody
	public Obj get(Integer id){
		Obj obj = (Obj) redisTemplate.boundHashOps("objList").get(id);
		return obj;
	}
	@RequestMapping("/testInc")
	@ResponseBody
	public String testInc(String key) throws ExecutionException, InterruptedException {
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			Future<Long> result = executor.submit(() -> redisUtil.getIncre(key));
			System.out.println("current thread:" + Thread.currentThread().getName() + " result:" + result.get());
		}

		executor.shutdown();

		return "success";
	}


	@Data
	@AllArgsConstructor
	private static class Obj implements Serializable {
		private static final long serialVersionUID = 6292950495924967341L;
		private Integer id;
		private Integer count;
	}
}
