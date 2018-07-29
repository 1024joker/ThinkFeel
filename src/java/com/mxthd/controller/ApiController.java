package com.mxthd.controller;

import com.mxthd.bean.Mail;
import com.mxthd.service.MailService;
import com.mxthd.service.UserService;
import com.mxthd.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    /**
     *  发送注册邮件
     */
    @RequestMapping(value = "/signup_sendmail",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult signup_sendmail(String mail){
        //校验邮箱
        if(!DataValidator.check(DataValidator.REGEX_EMAIL,mail))
            return new JsonResult("邮箱格式不正确");
        if(userService.findByEmail(mail)!=null)
            return new JsonResult("该邮箱已是会员,请直接登陆");
        if(JedisPoolUtils.getjedis().exists(mail) && JedisPoolUtils.getjedis().ttl(mail) > 30*60-60)
            //数据库存在key为mail的值 && 距上次发送邮件没超过一分钟    ps:说明访客绕过前端验证
            return new JsonResult("操作频繁,请稍后重试");
        String code = CodeUtils.getCode(8);//随机8位验证码
        try {
            mailService.signup(new Mail(mail),code);//执行邮件操作
            JedisPoolUtils.getjedis().setex(mail,30*60,code); //将mail和code保存到redis中
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("系统繁忙,请稍后重试");//邮件发送失败
        }
        return new JsonResult(JsonResult.SUCCESS,"邮件发送成功,请注意查收!");
    }
    /**
     * 处理验证码进入注册页面
     */
    @RequestMapping("/signup")
    public ModelAndView signup(@RequestParam(value = "mail",required = true) String mail,
                               @RequestParam(value = "code",required = true) String code){
        ModelAndView modelAndView = new ModelAndView();
        if(!JedisPoolUtils.getjedis().exists(mail) || !JedisPoolUtils.getjedis().get(mail).equals(code)){//如果redis不存在这个验证码 直接返回
            modelAndView.setViewName("signup_sendmail");
            modelAndView.addObject("msg","验证码无效或已过期,请重新提交注册!");
            return modelAndView;
        }
        modelAndView.setViewName("signup");
        modelAndView.addObject("hmail",mail);
        modelAndView.addObject("hcode",code);
        return modelAndView;
    }

}
