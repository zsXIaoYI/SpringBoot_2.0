package cn.zsza.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created By zhangsong
 * 21:08 2018/8/29
 */
@Data
@AllArgsConstructor
public class ErrorResponseEntity {
	private Integer code;
	private String message;
}
