package cn.zsza.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created By zhangsong
 * 23:37 2018/8/28
 */
@Data
@AllArgsConstructor
public class MsgResponse {
	private Integer flag;

	private String msg;
}
