package com.mxthd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.Theme;
import com.mxthd.bean.Topic;
import com.mxthd.service.ThemeService;
import com.mxthd.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    ThemeService themeService;

    @RequestMapping("/go/{slug}")
    public ModelAndView goTopic(@PathVariable("slug") String slug, @RequestParam(value = "p",defaultValue = "1") Integer p){
        ModelAndView modelAndView = new ModelAndView();
        Topic bySlug = topicService.findBySlug(slug);
        if (bySlug == null) {
            //建议跳转到
            //是否创建这个话题呢？
        }
        //在查询之前只需要调用。传入页码以及每页的大小
        PageHelper.startPage(p,10);
        List<Theme> byTopid = themeService.findByTopid(bySlug.getId());
        PageInfo page = new PageInfo(byTopid,5);//连续显示5页
        modelAndView.setViewName("topic");
        modelAndView.addObject("topic",bySlug);
        modelAndView.addObject("page",page);
        return modelAndView;
    }
    @RequestMapping("/")
    public ModelAndView home(@RequestParam(value = "p",defaultValue = "1") Integer p){
        ModelAndView modelAndView = new ModelAndView();
        //在查询之前只需要调用。传入页码以及每页的大小
        PageHelper.startPage(p,10);
        List<Theme> result = themeService.findAll();
        PageInfo page = new PageInfo(result,5);//连续显示5页
        modelAndView.setViewName("home");
        modelAndView.addObject("page",page);
        return modelAndView;
    }
}
