package cn.zsza.dao;

import cn.zsza.Vo.GenderVo;

/**
 * Created By zhangsong
 * 20:30 2018/8/27
 */
public interface GenderDao {
	void saveOne(GenderVo genderVo);

	GenderVo findOne(Integer id);
}
