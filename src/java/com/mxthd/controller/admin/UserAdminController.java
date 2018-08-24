package com.mxthd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.User;
import com.mxthd.service.UserService;
import com.mxthd.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserAdminController {
    @Autowired
    UserService userService;
    /**
     * 后台查询所有用户的信息*/
    @RequestMapping("/admin/users")
    @ResponseBody
    public JsonResult finAllUser(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        //引入分页插件
        PageHelper.startPage(pn,10);//从第一页开始，每页显示5条数据
        //startpage后面紧跟的查询这个查询就是一个分页查询
        List<User> users=userService.findAllUser();
        PageInfo pageInfo = new PageInfo(users,5);
        //返回json数据
        JsonResult json = new JsonResult(0,"pageInfo",pageInfo);
        return json;
    }
    @RequestMapping("/ht/users")
    public String togoodsInfo(){
        return "admin/userInfo";
    }
}
