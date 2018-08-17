package com.mxthd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ht")
public class GoodsHtmlController {
    @RequestMapping("/goods")
    public String togoodsInfo(){
        return "admin/goodsInfo";
    }
}
