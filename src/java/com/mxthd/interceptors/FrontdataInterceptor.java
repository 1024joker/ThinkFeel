package com.mxthd.interceptors;

import com.mxthd.bean.Options;
import com.mxthd.service.OptionsService;
import com.mxthd.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 传递前台页面所需要的数据
 */
public class FrontdataInterceptor implements HandlerInterceptor {
    @Autowired
    OptionsService optionsService;
    @Autowired
    ThemeService themeService;

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       //如果返回json，modelAndView是空的
        if(modelAndView!=null&&!modelAndView.getViewName().startsWith("redirect:")&&!modelAndView.getViewName().startsWith("forward:")){
           //网站名
           modelAndView.addObject("siteName",optionsService.getByKey("title").getOptionValue());
            //网站路径
            modelAndView.addObject("url",optionsService.getByKey("url").getOptionValue());
           //会员数
           modelAndView.addObject("usersNum",543);
           //话题数
           modelAndView.addObject("topicsNum",543);
           //热评主题
            modelAndView.addObject("renpingThemes",themeService.findRenPing());

       }
    }
}
