package com.mxthd.controller;

import com.mxthd.bean.User;
import com.mxthd.service.CardCodeService;
import com.mxthd.service.GoodsInfoService;
import com.mxthd.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodsController {
    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    CardCodeService cardCodeService;

    @RequestMapping(value = "/gift/{id:\\d+}",method = RequestMethod.GET)
    public ModelAndView giftGoods(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("giftpage");
        modelAndView.addObject("goods",goodsInfoService.getById(id));
        goodsInfoService.click(id);
        return modelAndView;
    }
    @RequestMapping(value = "/gift/{id:\\d+}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> guomai(@PathVariable("id") Integer id, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_user");
        if(user == null){
            return new JsonResult("请先登陆");
        }
        //查询是否还有卡密，并更新库存
        int countByGood = cardCodeService.findCountByGood(id);
        goodsInfoService.updateBystock(id,countByGood);
        if(countByGood<=0){
            return new JsonResult("库存不足");
        }
        return new JsonResult(JsonResult.SUCCESS,"兑换成功",null);
    }
}
