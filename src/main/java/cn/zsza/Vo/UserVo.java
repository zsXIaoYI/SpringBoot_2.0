package cn.zsza.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Random;

/**
 * Created By zhangsong
 * 21:56 2018/7/15
 */
@Data
public class UserVo {

	private Integer uid;
	private String userName;
	/**
	 * 用以给前端展示
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	private Date birthday;

	/**
	 * 用以接收请求中的参数转换，如birthday=1995-02-25
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	public static void main(String[] args) {
		int result = new Random().nextInt(10000);


		System.out.println(result);
	}

}
