package com.mxthd.controller;

import com.mxthd.bean.Theme;
import com.mxthd.bean.Topic;
import com.mxthd.service.ThemeService;
import com.mxthd.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    ThemeService themeService;

    @RequestMapping("/go/{slug}")
    public ModelAndView goTopic(@PathVariable("slug") String slug){
        ModelAndView modelAndView = new ModelAndView();
        Topic bySlug = topicService.findBySlug(slug);
        if (bySlug == null) {
            //建议跳转到
            //是否创建这个话题呢？
        }
        List<Theme> byTopid = themeService.findByTopid(bySlug.getId());
        modelAndView.setViewName("topic");
        modelAndView.addObject("topic",bySlug);
        modelAndView.addObject("themes",byTopid);
        return modelAndView;
    }
}
