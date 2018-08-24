package com.mxthd.controller;

import com.mxthd.bean.Comments;
import com.mxthd.bean.User;
import com.mxthd.service.CommentsService;
import com.mxthd.service.ThemeService;
import com.mxthd.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.mxthd.bean.Theme;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class ThemeController {
    @Autowired
    ThemeService themeService;
    @Autowired
    CommentsService commentsService;

    @RequestMapping("/theme/new")
    @ResponseBody
    public JsonResult<String> newTheme(String title, String context, Integer topicId,
                                 @RequestParam(value = "tag[]") String[] tag, HttpServletRequest request){
        //将标签存放在set中 防止重复
        Set<String> ttag = new HashSet<>();
        for (String s : tag) {
            ttag.add(s);
        }
        User user = (User)request.getSession().getAttribute("login_user");//获取登陆用户
        Theme theme = new Theme();
        theme.setTitle(title);
        theme.setContent(context);
        theme.setTopicId(topicId);
        theme.setUser(user);
        try {
            themeService.addTheme(theme,ttag);
        }catch (Exception e){
            //发布错误
            JsonResult<String> result = new JsonResult(JsonResult.SUCCESS,"发布错误");
            return result;
        }
        JsonResult<String> result = new JsonResult(JsonResult.SUCCESS,"发布成功",theme.getId());
        return result;
    }

    @RequestMapping("/t/{id}")
    public ModelAndView tTheme(@PathVariable("id") Integer id){
        ModelAndView modelAndVie = new ModelAndView();
        modelAndVie.setViewName("theme");
        Theme theme = themeService.findByid(id);
        if(theme==null){
            //?
        }
        themeService.addClick(theme.getId());
        modelAndVie.addObject("theme",theme);
        List<Comments> byTid = commentsService.findByTid(theme.getId());
        modelAndVie.addObject("comments", byTid);
        return modelAndVie;
    }

}
