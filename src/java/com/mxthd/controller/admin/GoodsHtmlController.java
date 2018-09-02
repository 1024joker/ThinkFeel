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
    public ModelAndView GoodsEdit (@RequestParam(value = "editId") Integer editId
            ,@RequestParam(value = "pageNum") Integer pageNum) {
        ModelAndView modelAndView = new ModelAndView("admin/goodsEdit");
        modelAndView.addObject("editId", editId);
        modelAndView.addObject("pageNum", pageNum);
        return modelAndView;
    }
    /*添加商品信息跳转 页面*/
    @RequestMapping("/addGoods")
    public String addGoods(){
        return "admin/goodsEdit";
    }

    /*商品类型的条跳转页面*/
    @RequestMapping("/typeGoods")
    public String typeGoods(){
        return "admin/goodsType";
    }
}
