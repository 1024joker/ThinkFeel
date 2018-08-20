package com.mxthd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.Goods;
import com.mxthd.service.GoodsInfoService;
import com.mxthd.util.JsonResult;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class GoodsInfoController {
    @Autowired
    GoodsInfoService goodsInfoService;
    /*
    * 查询所有员工
    * */
    @RequestMapping("/goods")
    @ResponseBody
    public JsonResult getAllGoods(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        //引入分页插件
        PageHelper.startPage(pn,10);//从第一页开始，每页显示5条数据
        //startpage后面紧跟的查询这个查询就是一个分页查询
        List<Goods> goods = goodsInfoService.getGoodsInfo();
        //导航页码数5页
        PageInfo pageInfo = new PageInfo(goods,5);
        //返回json数据
        JsonResult json = new JsonResult(0,"pageInfo",pageInfo);
        return json;
    }

}
