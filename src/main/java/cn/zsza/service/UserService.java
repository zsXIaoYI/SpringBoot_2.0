package cn.zsza.service;

import cn.zsza.Page.PageBean;
import cn.zsza.Vo.UserVo;
import cn.zsza.dao.Log4jDao;
import cn.zsza.dao.UserDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZhangSong
 * @Date: 2018/6/16 15:20
 * @Company: NoNo
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Value("${name.aa}")
    private String name;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Log4jDao log4jDao;

    public UserVo findOne(Integer uid){
        return userDao.findOne(uid);
    }

    public List<UserVo> findAll(){
        log4jDao.testLog();
        List<UserVo> userVoList = userDao.findAll();
        LOGGER.info("userVoList size:" + userVoList.size());
        return userVoList;
    }
    public String getNe(){

        return name;
    }

    public PageBean<UserVo> getPage(Integer page,Integer pageSize){

        PageHelper.startPage(page,pageSize);
        List<UserVo> userVoList = userDao.findAll();

        PageBean<UserVo> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setPageSize(pageSize);
        pageBean.setData(userVoList);
        return pageBean;
    }
}
