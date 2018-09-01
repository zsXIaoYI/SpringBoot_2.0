package cn.zsza.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created By zhangsong
 * 22:52 2018/8/28
 */
@Data
public class U implements Serializable {

	private static final long serialVersionUID = -8243112671536283885L;
	private String name;
	private String password;

	private Integer val;
}
