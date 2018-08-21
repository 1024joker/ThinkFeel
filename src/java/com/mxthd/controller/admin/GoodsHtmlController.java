package com.mxthd.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ht")
public class GoodsHtmlController {
    @RequestMapping("/goods")
    public String togoodsInfo(){
        return "admin/goodsInfo";
    }

    /*编辑商品信息*/
    @RequestMapping("/editGoods")
        public String GoodsEdit () {
            return "admin/goodsEdit";
    }
}
