package cn.zsza.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By zhangsong
 * 23:21 2018/8/5
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class It {

	private Integer id;
	private String name;


	public static void main(String[] args) {
		ItBuilder builder = It.builder();
		builder.id(1);
		builder.name("小A");



		System.out.println(	builder.build());
	}
}
