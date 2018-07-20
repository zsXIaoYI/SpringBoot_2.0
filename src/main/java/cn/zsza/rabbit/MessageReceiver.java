package cn.zsza.rabbit;

import cn.zsza.constant.RabbitConstant;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created By zhangsong
 * 16:42 2018/7/16
 */
@Service
public class MessageReceiver {


	@RabbitListener(queues = RabbitConstant.TEST_QUEUE)
	public void receiveMessage(final Message message){
		Object result = JSON.parse(new String(message.getBody()));
		System.out.println("message receiver: " + result);
	}

	@RabbitListener(queues = RabbitConstant.ZS_QUEUE)
	public void receiveMessageExcAndBinding(final Message message){
		Object result = JSON.parse(new String(message.getBody()));
		System.out.println("message receiver by ZS_QUEUE: " + result);
	}
}
