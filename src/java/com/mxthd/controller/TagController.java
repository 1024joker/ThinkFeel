package com.mxthd.controller;

import com.mxthd.bean.Tag;
import com.mxthd.service.TagService;
import com.mxthd.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ThemeService themeService;

    @RequestMapping("/tag/{name}")
    public ModelAndView viewTag(@PathVariable("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        Tag tag = tagService.getByName(name);
        if(tag==null){
            //跳转到？
        }
        modelAndView.setViewName("tag");
        modelAndView.addObject("tag",tag);
        modelAndView.addObject("themes",themeService.findBytagId(tag.getId()));
        return modelAndView;
    }
}
