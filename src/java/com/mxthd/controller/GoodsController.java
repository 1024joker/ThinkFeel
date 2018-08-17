package com.mxthd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoodsController {
    @RequestMapping("/gift/{id}")
    public ModelAndView giftGoods(@PathVariable("id") Integer id){
        //
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("giftpage");
        return modelAndView;
    }
}
