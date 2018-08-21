package com.mxthd.controller;

import com.mxthd.service.GoodsClassService;
import com.mxthd.service.GoodsInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoodsClassController {
    @Autowired
    GoodsClassService goodsClassService;
    @Autowired
    GoodsInfoService goodsInfoService;
    @RequestMapping("/gift")
    public ModelAndView showGoodsClass(@RequestParam(value = "t",required = false) Integer t,
                                       @RequestParam(value = "min",required = false) Integer min,
                                       @RequestParam(value = "max",required = false) Integer max){
        ModelAndView modelAndView = new ModelAndView("gift");
        modelAndView.addObject("goodsClass",goodsClassService.getAll());
        modelAndView.addObject("goods",goodsInfoService.getHome(t,min,max));
        modelAndView.addObject("t",t);
        modelAndView.addObject("min",min);
        modelAndView.addObject("max",max);
        return modelAndView;
    }
}
