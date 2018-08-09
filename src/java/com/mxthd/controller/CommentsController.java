package com.mxthd.controller;

import com.mxthd.bean.User;
import com.mxthd.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @RequestMapping("/theme/comment")
    public String add(@RequestParam("themeId") Integer tid,@RequestParam("content") String content,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_user");//获取登陆用户
        commentsService.add(content,tid,user.getId());
        return "redirect:/t/"+tid;
    }
}
