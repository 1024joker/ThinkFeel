package com.mxthd.controller;

import com.alibaba.fastjson.JSONObject;
import com.mxthd.bean.Pay;
import com.mxthd.bean.User;
import com.mxthd.service.PayService;
import com.mxthd.util.eapayUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PayController {
    @Autowired
    PayService payService;
    String zfURL = "https://api.eapay.cc/v1/order/pay/no/";
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public ModelAndView pay(String msg,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("pay");
        User user = (User)request.getSession().getAttribute("login_user");//获取登陆用户
        modelAndView.addObject("pays",payService.findByUid(user.getId()));
        modelAndView.addObject("msg",msg);
        return modelAndView;
    }

    /**
     * 发起支付
     * @param money
     * @param request
     * @return
     */
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public ModelAndView modelAndView(Double money,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        JSONObject json = null;
        Pay pay = new Pay();
        try {
            pay.setMoney(money);
            pay.setPpid(eapayUtils.uuid());
            User user = (User)request.getSession().getAttribute("login_user");//获取登陆用户
            pay.setUid(user.getId());
            String result =payService.orderAdd(pay);
            json = JSONObject.parseObject(result);
            System.out.println("====================="+json);
        }catch (Exception e){
        }
        if(json==null||json.getBoolean("status")==null||!json.getBoolean("status")){
            modelAndView.setViewName("forward:/pay");
            modelAndView.addObject("msg","订单创建失败,请稍后重试！");
            return modelAndView;
        }
        String no = json.getJSONObject("data").getString("no");
        pay.setNo(no);
        payService.payAdd(pay);
        modelAndView.setViewName("redirect:"+zfURL+no);
        return modelAndView;
    }

    /**
     * 异步回调
     */
    @RequestMapping(value = "/pay/index",method = RequestMethod.POST)
    @ResponseBody
    public String payHuidiao(@Param("out_trade_no") String out_trade_no,//商户订单号 ppid
                             @Param("pay_method") Integer pay_method,//用户选择的支付方式，例如：1 代表 支付宝
                             @Param("total_fee") Double total_fee,//商户订单总金额，单位为元(CNY))
                             @Param("trade_no") String trade_no,//支付平台返回订单号
                             @Param("sign") String sign,HttpServletRequest request) {
        int i=0;
        try {
            Pay pay = payService.findByPPID(out_trade_no);
            i = payService.payHuidiao(pay.getId(), total_fee, pay.getUid());
        }catch (Exception e){
            i=0;
        }
        if(i==0){
            return "FAIL";
        }
        return "SUCCESS";
    }
    //重新支付页面
    @RequestMapping("/pay/{no}")
    public ModelAndView chongxzhif(@PathVariable("no") String no){
        ModelAndView modelAndView = new ModelAndView("redirect:"+zfURL+no);
        return modelAndView;
    }
    //充值返回页面
    @RequestMapping("/pay/ok")
    public ModelAndView payok(){
        ModelAndView modelAndView = new ModelAndView("redirect:/pay?msg=%E5%85%85%E5%80%BC%E6%88%90%E5%8A%9F");
        return modelAndView;
    }

}
