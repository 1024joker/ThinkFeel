package com.mxthd.controller;

import com.mxthd.bean.User;
import com.mxthd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenberController {
    @Autowired
    UserService userService;
    @RequestMapping("/member/{username}")
    public ModelAndView member(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        ModelAndView modelAndView = new ModelAndView("member");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
