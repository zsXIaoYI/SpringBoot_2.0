package cn.zsza.dao;

import cn.zsza.Vo.UserVo;

import java.util.List;

/**
 * @Author: ZhangSong
 * @Date: 2018/6/16 15:47
 * @Company: NoNo
 */
public interface UserDao {

	public List<UserVo> findAll();

	public void saveOne(UserVo userVo);

	public UserVo findOne(Integer uid);

}
