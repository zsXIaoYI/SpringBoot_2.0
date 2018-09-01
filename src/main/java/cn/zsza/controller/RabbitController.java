package cn.zsza.controller;

import cn.zsza.Vo.CountVo;
import cn.zsza.Vo.GenderVo;
import cn.zsza.Vo.U;
import cn.zsza.annotation.MyAnnotation;
import cn.zsza.constant.RabbitConstant;
import cn.zsza.dao.GenderDao;
import cn.zsza.rabbit.MessageSender;
import cn.zsza.service.CountService;
import cn.zsza.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
/**
 * Created By zhangsong
 * 16:47 2018/7/16
 */
@Controller
@RequestMapping("rabbit")
public class RabbitController {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private MessageSender messageSender;

	@RequestMapping("/send")
	@ResponseBody
	public String send(){
		messageSender.sendMessage("hello rabbit, nice");
		return "success";
	}
	@RequestMapping("/sendObj")
	@ResponseBody
	public String sendObj(){
		messageSender.sendMessage(new Info(11,"xxxx"));
		return "success";
	}
	@RequestMapping("/sendObjExcAndBind")
	@ResponseBody
	public String sendObjectExcAndBinding(){
		messageSender.sendMessageByExcBinding(RabbitConstant.ZS_EXCHANGE_NAME,
				"zs.xx",new Info(11,"李三"));
		return "success";
	}

	@Autowired
	private CountService countService;
	@Autowired
	private GenderDao genderDao;

	@RequestMapping("/update")
	@ResponseBody
	public String update(CountVo countVo){
		countService.updateById(countVo);
		return "succ";
	}

	@RequestMapping("/gd")
	@ResponseBody
	public GenderVo findOne(Integer id){
		return genderDao.findOne(id);
	}

	@RequestMapping("/myAn")
	@ResponseBody
	@MyAnnotation(val = 2)
	public String testMyAnnotation(Integer id){
		System.out.println("id:" + id);
		return "success";
	}

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/login")
	@ResponseBody
	public String login(String name, String password,Integer val){
		U u = userService.getU(name, password,val);
		if (u != null){
			String id = request.getSession().getId();
			redisTemplate.opsForValue().set("session:" + id, u);
		}
		return "success";
	}

	@RequestMapping("/sen")
	@ResponseBody
	public String testSession(){
		HttpSession session = request.getSession(false);
		System.out.println("session:" + session);
		return "success";
	}

	@Data
	@AllArgsConstructor
	static class Info implements Serializable {
		private static final long serialVersionUID = -4477978817571858916L;

		private Integer id;
		private String name;
	}
}
