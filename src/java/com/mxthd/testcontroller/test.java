package com.mxthd.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class test {
    private static final String SUCCESS="success";


    @RequestMapping("/test")
    public ModelAndView test1(){
        ModelAndView mv = new ModelAndView("admin/goodsInfo");
        return mv;
    }
}
