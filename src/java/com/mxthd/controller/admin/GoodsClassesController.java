package com.mxthd.controller.admin;

import com.mxthd.bean.Goods;
import com.mxthd.bean.GoodsClass;
import com.mxthd.service.GoodsClassService;
import com.mxthd.service.GoodsInfoService;
import com.mxthd.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goodsType")
public class GoodsClassesController {
    @Autowired
    GoodsClassService goodsClassService;

    @RequestMapping("/addGoodsType")
    @ResponseBody
    /*增加一个商品类型*/
    public Integer addGoodsType(GoodsClass goodsClass){
        int i = goodsClassService.addGoodsType(goodsClass);
        return i;
    }

    @RequestMapping("/getGoodsType")
    @ResponseBody
    /*查询所有商品类型*/
    public JsonResult getGoodsType(@RequestParam("id") Integer id){
        GoodsClass all = goodsClassService.getGoodsTypeById(id);
        JsonResult jsonResult = new JsonResult(0,"getGoodsType",all);
        return jsonResult;
    }
}
