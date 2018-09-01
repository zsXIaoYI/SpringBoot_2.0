package cn.zsza.service;

import cn.zsza.Page.PageBean;
import cn.zsza.Vo.U;
import cn.zsza.Vo.UserVo;
import cn.zsza.dao.Log4jDao;
import cn.zsza.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangSong
 * @Date: 2018/6/16 15:20
 * @Company: NoNo
 */
@Service
public class UserService {

    private static Map<String, String> map = ImmutableMap.
            of("u1","p1","u2","p2");

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


    public U getU(String name,String password,Integer val){
        String pwd = map.get(name);
        if (Objects.equal(pwd,password)){
            U u = new U();
            u.setName(name);
            u.setPassword(pwd);
            u.setVal(val);
            return u;
        }
        return null;
    }


}
