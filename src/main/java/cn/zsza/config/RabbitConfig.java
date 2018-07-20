package cn.zsza.config;

import cn.zsza.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created By zhangsong
 * 18:14 2018/7/16
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue zsQueue(){
		return new Queue(RabbitConstant.ZS_QUEUE);
	}

	@Bean
	public TopicExchange zsTopicExchange(){
		return new TopicExchange(RabbitConstant.ZS_EXCHANGE_NAME);
	}

	@Bean
	public Binding zsBinding(){
		return BindingBuilder.
				bind(zsQueue()).
				to(zsTopicExchange()).with(RabbitConstant.ZS_ROUTING_KEY);
	}
}
