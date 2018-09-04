package com.mxthd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.LoginLog;
import com.mxthd.bean.User;
import com.mxthd.dao.LoginLogMapper;
import com.mxthd.service.LoginLogService;
import com.mxthd.service.UserService;
import com.mxthd.util.JedisPoolUtils;
import com.mxthd.util.JsonResult;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LoginLogService loginLogService;

    /**
     * 用户设置
     * @param session
     * @return
     */
    @RequestMapping("/settings")
    public ModelAndView settings(HttpSession session){
        User user = (User)session.getAttribute("login_user");
        User user1 = userService.loginCheck(user.getUsername(), user.getPassword());
        ModelAndView modelAndView = new ModelAndView("settings");
        modelAndView.addObject("user",user1);
        return modelAndView;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public ModelAndView addUser(User u,String hmail,String hcode){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setUsername(u.getUsername());
        if(u.getUsername().length()<5 || u.getPassword().length()<5){
            modelAndView.setViewName("forward:/api/signup?mail="+hmail+"&&code="+hcode);
            modelAndView.addObject("user",u);
            modelAndView.addObject("msg","用户名和密码长度必须>=5");
            return modelAndView;
        }
        if(userService.findByUsername(u.getUsername())!=null){
            modelAndView.setViewName("forward:/api/signup?mail="+hmail+"&&code="+hcode);
            modelAndView.addObject("user",u);
            modelAndView.addObject("msg","用户名已存在");
            return modelAndView;
        }
        if(!JedisPoolUtils.getjedis().exists(hmail) || !JedisPoolUtils.getjedis().get(hmail).equals(hcode)){//如果redis不存在这个验证码 直接返回
            modelAndView.setViewName("forward:/signup");
            modelAndView.addObject("msg","验证码无效或已过期,请重新提交注册!");
            return modelAndView;
        }
        user.setPassword(u.getPassword());
        user.setEmail(hmail);
        userService.addUser(user);
        JedisPoolUtils.getjedis().del(hmail);
        modelAndView.setViewName("404");
        return modelAndView;
    }

    /**
     * 修改头像
     */
    public JsonResult updateAvatar(String avatar,HttpSession httpSession){
        return null;
    }

    /**
     *  用户登陆
     * @return
     */
    @RequestMapping("/loginCheck")
    @ResponseBody
    public JsonResult loginCheck(String username, String password, HttpServletRequest request, HttpSession session){
        User user = userService.loginCheck(username, password);
        if(user==null)
            return new JsonResult("用户名或密码错误");
        session.setAttribute("login_user",user);
        try {
            //获取登录信息
            String ip = getRemortIP(request);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            //获取用户的浏览器名
            String userbrowser = userAgent.getBrowser().toString();
            //写入登录日志
            LoginLog log=new LoginLog();
            log.setUid(user.getId());
            log.setIp(ip);
            log.setDevice(userbrowser);
            System.out.println("====================="+log);
            loginLogService.addLog(log);
        }catch (Exception e){

        }finally {
            return new JsonResult(JsonResult.SUCCESS,"登陆成功，跳转中...");
        }
    }


    /**
     * 用户退出
     */
    @RequestMapping("/signout")
    public ModelAndView signout(HttpSession httpSession){
        httpSession.removeAttribute("login_user");
        return new ModelAndView("redirect:/");
    }
    /**
     * 获取客户端IP
     */
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }



}
