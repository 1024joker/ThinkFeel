package com.mxthd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.Goods;
import com.mxthd.bean.GoodsClass;
import com.mxthd.service.GoodsClassService;
import com.mxthd.service.GoodsInfoService;
import com.mxthd.util.JsonResult;
import com.sun.net.httpserver.Authenticator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class GoodsInfoController {
    @Autowired
    GoodsClassService goodsClassService;
    @Autowired
    GoodsInfoService goodsInfoService;
    /*
    * 查询所有商品信息
    * */
    @RequestMapping("/goods")
    @ResponseBody
    public JsonResult getAllGoods(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        //引入分页插件
        PageHelper.startPage(pn,10);//从第一页开始，每页显示5条数据
        //startpage后面紧跟的查询这个查询就是一个分页查询
        List<Goods> goods = goodsInfoService.getGoodsInfo(null);
        //导航页码数5页
        PageInfo pageInfo = new PageInfo(goods,5);
        //返回json数据
        JsonResult json = new JsonResult(0,"pageInfo",pageInfo);
        return json;
    }
    /*
     * 根据标题查询带条件的商品信息
     * */
    @RequestMapping("/getGoodsByTitle")
    @ResponseBody
    public JsonResult getGoodsByTitle(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                                      @Param("title") String title){
        PageHelper.startPage(pn,10);
        List<Goods> goods1 = goodsInfoService.getGoodsByTitle(title);
        PageInfo pageInfo = new PageInfo(goods1,5);
        JsonResult json = new JsonResult(0,"pagesByTitle",pageInfo);
        return json;
    }
    /*
    * 加载编辑商品信息页面时，返回商品类型数据给下拉框
    * */
    @RequestMapping("/getGoodsType")
    @ResponseBody
    public JsonResult getGoodsType(){
        List<GoodsClass> goodsClasses = goodsClassService.getAll();
        JsonResult json = new JsonResult(0,"goodsClass",goodsClasses);
        return json;
    }
    /*
    * 新增商品
    * */
    @RequestMapping(value = "/addGoods",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addGoods(Goods goods){
        int i = goodsInfoService.addGoods(goods);
        if (i>0) {
            PageHelper.startPage(1,10);
            List<Goods> goods1 = goodsInfoService.getGoodsInfo(null);
            //导航页码数5页
            PageInfo pageInfo = new PageInfo(goods1,5);
            //返回json数据
            JsonResult json = new JsonResult(0,"lastpage",pageInfo.getPages());
            return json;
        }
        return new JsonResult("失败");
    }
}
