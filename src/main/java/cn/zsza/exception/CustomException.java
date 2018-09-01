package cn.zsza.exception;

/**
 * Created By zhangsong
 * 21:04 2018/8/29
 */
public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 7871088247829015427L;
	private Integer code;

	public CustomException() {
		super();
	}

	public CustomException(Integer code, String message) {
		super(message);
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
