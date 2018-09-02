package com.mxthd.controller;

import com.mxthd.bean.Mail;
import com.mxthd.bean.User;
import com.mxthd.service.MailService;
import com.mxthd.service.UserService;
import com.mxthd.util.*;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    @RequestMapping("/qq/afterlogin")
    @ResponseBody()
    public ModelAndView qqafterlogin(HttpServletRequest request){
        User login_user = (User)request.getSession().getAttribute("login_user");
        String accessToken = null,
                openId  = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            if(!accessTokenObj.getAccessToken().equals("")){
                accessToken = accessTokenObj.getAccessToken();
                //获取Openid
                OpenID openIDObj = new OpenID(accessToken);
                openId = openIDObj.getUserOpenID();
                if(login_user!=null){
                    //用户绑定
                    //数据库是否存在这个openid
                    userService.updateQQOpenid(login_user.getId(),openId);
                    modelAndView.setViewName("redirect:/user/settings");
                }else{
                    //用户登陆
                    User qqlogin = userService.qqlogin(openId);
                    if(qqlogin!=null){
                        request.getSession().setAttribute("login_user",qqlogin);
                    }
                    modelAndView.setViewName("redirect:/");
                }
            }else {
                modelAndView.setViewName("redirect:/");
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @RequestMapping("/qqlogin")
    public ModelAndView qqlogin(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("redirect:"+new Oauth().getAuthorizeURL(request));
        } catch (Exception e) {
            modelAndView.setViewName("/");
        }
        return modelAndView;
    }

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

    /**
     * 编辑器上传图片插件
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload/editormdPic",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String uploadEditormdpic(
            @RequestParam(value = "editormd-image-file",required = true)MultipartFile file,
            HttpServletRequest request,HttpServletResponse response){
        String trueFileName = file.getOriginalFilename();//临时获取文件名
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));//获取文件后缀.jpg
        String fileName = System.currentTimeMillis()+"_"+CodeUtils.getCode(6)+suffix;//生成文件名xxx.jpg
        String path = request.getSession().getServletContext().getRealPath("/static/img/upload/");
        try {
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            //保存
            file.transferTo(targetFile);
        }catch (Exception e){
            return "{\"success\": 0, \"message\": \"上传失败\"}";
        }
        return "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/static/img/upload/" + fileName + "\"}";
    }
    /**
     * 访问用户信息
     * @return
     */



}
