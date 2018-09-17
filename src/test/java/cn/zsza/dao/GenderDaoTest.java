package cn.zsza.dao;

import cn.zsza.BaseTest;
import cn.zsza.Vo.GenderVo;
import cn.zsza.enums.Gender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created By zhangsong
 * 20:37 2018/8/27
 */
public class GenderDaoTest extends BaseTest {

	@Autowired
	private GenderDao genderDao;

	@Test
	public void saveOne() {
		GenderVo genderVo = new GenderVo();

		genderVo.setName("小白");
		genderVo.setGender(Gender.FEMALE);
		genderDao.saveOne(genderVo);
	}

	@Test
	public void findOne() {

		GenderVo genderVo = genderDao.findOne(1);
		System.out.println(genderVo);
	}


}