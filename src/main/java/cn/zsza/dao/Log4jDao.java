package cn.zsza.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created By zhangsong
 * 12:23 2018/7/18
 */
@Repository
public class Log4jDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Log4jDao.class);

	public void testLog(){
		LOGGER.error("error log4jDao.....");
	}
}
