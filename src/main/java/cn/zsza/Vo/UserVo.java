package cn.zsza.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
/**
 * Created By zhangsong
 * 21:56 2018/7/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

	private static final long serialVersionUID = -8408424174426381987L;
	private Integer uid;
	private String userName;
	/**
	 * 用以给前端展示
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date birthday;

	/**
	 * 用以接收请求中的参数转换，如birthday=1995-02-25
	 */
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date birthday;

}
