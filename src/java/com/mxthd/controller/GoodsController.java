package com.mxthd.controller;

import com.mxthd.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoodsController {
    @Autowired
    GoodsInfoService goodsInfoService;

    @RequestMapping("/gift/{id}")
    public ModelAndView giftGoods(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("giftpage");
        modelAndView.addObject("goods",goodsInfoService.getById(id));
        return modelAndView;
    }
}
