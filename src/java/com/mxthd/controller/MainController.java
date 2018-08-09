package com.mxthd.controller;

import com.mxthd.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理跳转html页面
 */
@Controller
public class MainController {
    @Autowired
    TopicService topicService;
    /**
     * 进入发帖页面
     */
    @RequestMapping("/new")
    public ModelAndView New(){
        ModelAndView signupPage=new ModelAndView("new");
        signupPage.addObject("topics",topicService.findAll());
        return  signupPage;
    }
    /**
     * 进入mail注册页面.
     */
    @RequestMapping("/signup")
    public ModelAndView signup(){
        ModelAndView signupPage=new ModelAndView("signup_sendmail");
        return  signupPage;
    }
    /**
     * 进入mail登陆页面.
     */
    @RequestMapping("/signin")
    public ModelAndView signin(){
        ModelAndView signupPage=new ModelAndView("signin");
        return  signupPage;
    }
    /**
     * 404页面
     * @return
     */
    @RequestMapping("*")
    public ModelAndView notfound(){
        ModelAndView signupPage=new ModelAndView("404");
        return  signupPage;
    }


}
