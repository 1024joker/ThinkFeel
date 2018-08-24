package com.mxthd.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ht")
public class GoodsHtmlController {
    @RequestMapping("/goods")
    public ModelAndView togoodsInfo(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        ModelAndView modelAndView = new ModelAndView("admin/goodsInfo");
        modelAndView.addObject("pn_n",pn);
        return modelAndView;
    }

    /*编辑商品信息*/
    @RequestMapping("/editGoods")
        public String GoodsEdit () {
            return "admin/goodsEdit";
    }
}
