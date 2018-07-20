package cn.zsza.controller;

import cn.zsza.Page.PageBean;
import cn.zsza.Vo.UserVo;
import cn.zsza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author: ZhangSong
 * @Date: Created In 9:39 2018/4/28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("to")
    public String receiveOne(UserVo userVo){
        System.out.println(userVo.toString());
        return "success";
    }

    @RequestMapping(value = "test",method = {RequestMethod.GET})
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("name","小A");
        map.put("age","18");
        return map;
    }
    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    public String getHello(String name,Integer age){
        System.out.println("name:" + name + " {...} " + "age:" + age);
        return "hello";
    }

    @RequestMapping(value = "getNe",method = {RequestMethod.GET})
    @ResponseBody
    public String getName(){
        return userService.getNe();
    }

    @RequestMapping(value = "findAll",method = {RequestMethod.GET})
    @ResponseBody
    public List<UserVo> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "getPage",method = {RequestMethod.GET})
    @ResponseBody
    public PageBean<UserVo> getPage(@RequestParam(defaultValue = "1")Integer page,
                                    @RequestParam(defaultValue = "2")Integer pageSize){
        return userService.getPage(page,pageSize);
    }

    @RequestMapping(value = "findOne/{uid}",method = {RequestMethod.GET},params = {"aa=f"})
    @ResponseBody
    public UserVo findOne(@PathVariable(value = "uid") Integer uid){
        return userService.findOne(uid);
    }
}
