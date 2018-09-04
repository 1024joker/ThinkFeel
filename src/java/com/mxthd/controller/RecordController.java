package com.mxthd.controller;

import com.mxthd.bean.Record;
import com.mxthd.bean.User;
import com.mxthd.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping("/record")
    public ModelAndView record(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("login_user");
        List<Record> byUid = recordService.findByUid(user.getId());
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("record");
        modelAndView.addObject("records",byUid);
        return modelAndView;
    }
}
