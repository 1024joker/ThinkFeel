package com.mxthd.controller;

import com.mxthd.service.GoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoodsClassController {
    @Autowired
    GoodsClassService goodsClassService;

    @RequestMapping("/gift")
    public ModelAndView showGoodsClass(){
        ModelAndView modelAndView = new ModelAndView("gift");
        modelAndView.addObject("goodsClass",goodsClassService.getAll());
        return modelAndView;
    }
}
