package com.mxthd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.Goods;
import com.mxthd.service.GoodsClassService;
import com.mxthd.service.GoodsInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GoodsClassController {
    @Autowired
    GoodsClassService goodsClassService;
    @Autowired
    GoodsInfoService goodsInfoService;
    @RequestMapping("/gift")
    public ModelAndView showGoodsClass(@RequestParam(value = "t",required = false) Integer t,
                                       @RequestParam(value = "min",required = false) Integer min,
                                       @RequestParam(value = "max",required = false) Integer max,
                                       @RequestParam(value = "p",defaultValue = "1") Integer p){
        ModelAndView modelAndView = new ModelAndView("gift");
        modelAndView.addObject("goodsClass",goodsClassService.getAll());
        PageHelper.startPage(p,6);
        List<Goods> home = goodsInfoService.getHome(t, min, max);
        PageInfo page = new PageInfo(home,5);//连续显示5页
        modelAndView.addObject("goods",page);
        modelAndView.addObject("t",t);
        modelAndView.addObject("min",min);
        modelAndView.addObject("max",max);
        modelAndView.addObject("count",goodsInfoService.getHome(t, min, max).size());
        return modelAndView;
    }
}
