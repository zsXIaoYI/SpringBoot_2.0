package cn.zsza.controller;

import cn.zsza.constant.RabbitConstant;
import cn.zsza.rabbit.MessageSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
/**
 * Created By zhangsong
 * 16:47 2018/7/16
 */
@Controller
@RequestMapping("rabbit")
public class RabbitController {


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
	@Data
	@AllArgsConstructor
	static class Info implements Serializable {
		private static final long serialVersionUID = -4477978817571858916L;

		private Integer id;
		private String name;
	}
}
