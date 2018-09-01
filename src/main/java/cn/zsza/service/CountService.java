package cn.zsza.service;

import cn.zsza.Vo.CountVo;
import cn.zsza.dao.CountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created By zhangsong
 * 19:00 2018/8/24
 */
@Service
@Transactional
public class CountService {
	@Autowired
	private CountDao countDao;

	public void updateById(CountVo countVo){
		countDao.updateById(countVo);
	}
}
