package cn.zsza.dao;

import cn.zsza.BaseTest;
import cn.zsza.Vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created By zhangsong
 * 16:49 2018/7/18
 */
public class UserDaoTest extends BaseTest {

	@Autowired
	private UserDao userDao;
	@Test
	public void testSaveOne(){
		UserVo userVo = new UserVo();

		userVo.setUserName("李三");
		userVo.setBirthday(new Date());
		userDao.saveOne(userVo);
	}

	@Test
	public void testPage(){

		PageHelper.startPage(1,2);

		List<UserVo> userVoList = userDao.findAll();

		PageInfo<UserVo> userVoPageInfo = new PageInfo<>(userVoList);
		System.out.println(userVoPageInfo.getTotal());
		System.out.println(userVoList.size());
	}

	@Test
	public void testSaveAndReturn(){
		UserVo userVo = new UserVo();
		userVo.setUserName("李xx");
		userVo.setBirthday(new Date());
		Integer count = userDao.saveAndReturn(userVo);
		System.out.println("count:" + count);
		System.out.println("插入后主键为:" + userVo.getUid());
	}

	@Test
	public void findByName(){
		UserVo userVo = userDao.findByName("小A");
		System.out.println(userVo);
	}

}