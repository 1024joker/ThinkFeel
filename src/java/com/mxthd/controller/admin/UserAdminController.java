package com.mxthd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.Pay;
import com.mxthd.bean.User;
import com.mxthd.dao.PayMapper;
import com.mxthd.service.PayService;
import com.mxthd.service.UserService;
import com.mxthd.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserAdminController {
    @Autowired
    UserService userService;
    @Autowired
    PayService payService;
    /**
     * 后台查询所有用户的信息*/
    @RequestMapping("/admin/users")
    @ResponseBody
    public JsonResult finAllUser(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        //引入分页插件
        PageHelper.startPage(pn,8);//从第一页开始，每页显示10条数据
        //startpage后面紧跟的查询这个查询就是一个分页查询
        List<User> users=userService.findAllUser(null);
        PageInfo pageInfo = new PageInfo(users,8);
        //返回json数据
        JsonResult json = new JsonResult(0,"pageInfo",pageInfo);
        return json;
    }

    /*
     * 根据姓名查询带条件的商品信息
     * */
    @RequestMapping("/admin/getUsername")
    @ResponseBody
    public JsonResult getUsername(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                                      @RequestParam("username") String username){
        PageHelper.startPage(pn,8);
        List<User> users1 = userService.getUsername(username);
        System.out.println(username);
        PageInfo pageInfo = new PageInfo(users1,5);
        JsonResult json = new JsonResult(0,"pageByUsername",pageInfo);
        return json;
    }

    //在后台查询用户的消费记录
    @RequestMapping("/admin/selectByPay")
    @ResponseBody
    public JsonResult selectByPay(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                                  @RequestParam(value = "id")Integer id){
        PageHelper.startPage(pn,8);
        List<Pay> users = payService.findByUid(id);
      // System.out.println(id);
        PageInfo pageInfo = new PageInfo(users,5);
        JsonResult json = new JsonResult(0,"pageById",pageInfo);
        return json;

    }
    @RequestMapping("/ht/users")
    public String togoodsInfo(){
        return "admin/userInfo";
    }




}
