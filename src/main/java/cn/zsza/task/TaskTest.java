package cn.zsza.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * Created By zhangsong
 * 21:30 2018/7/24
 *
 * spring定时任务
 */
@Component
@EnableScheduling
public class TaskTest {

	@Scheduled(cron = "0 38 21 * * ?")
	public void print(){
		System.out.println("task is running");
	}
}
