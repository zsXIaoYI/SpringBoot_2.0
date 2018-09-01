package cn.zsza.Vo;

import cn.zsza.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By zhangsong
 * 20:27 2018/8/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderVo {
	private Integer id;
	private String name;

	private Gender gender;
}
