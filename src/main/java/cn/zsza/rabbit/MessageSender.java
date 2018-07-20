package cn.zsza.rabbit;

import cn.zsza.constant.RabbitConstant;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created By zhangsong
 * 16:08 2018/7/16
 */
@Service
public class MessageSender<T> {
	/**
	 * 被final修饰的成员变量要么初始化，要么在构造方法中初始化
	 */
	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public MessageSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	/**
	 * Topic Exchange
	 * Routing Key
	 * @param obj
	 */
	public void sendMessage(T obj){
		String res = JSON.toJSONString(obj);
		rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME,RabbitConstant.ROUTING_KEY,res);
	}
	/**
	 *
	 * @param exchange 指定交换机
	 * @param routingKey  指定Binding key
	 * @param obj
	 */
	public void sendMessageByExcBinding(String exchange,String routingKey,T obj){
		String res = JSON.toJSONString(obj);
		rabbitTemplate.convertAndSend(exchange,routingKey,res);
	}
}
