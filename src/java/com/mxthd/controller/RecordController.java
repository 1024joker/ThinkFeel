package com.mxthd.controller;

import com.mxthd.bean.CardCode;
import com.mxthd.bean.Record;
import com.mxthd.bean.User;
import com.mxthd.service.CardCodeService;
import com.mxthd.service.RecordService;
import com.mxthd.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    CardCodeService cardCodeService;

    @RequestMapping("/record")
    public ModelAndView record(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("login_user");
        List<Record> byUid = recordService.findByUid(user.getId());
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("record");
        modelAndView.addObject("records",byUid);
        return modelAndView;
    }
    @RequestMapping("/record/{id:\\d+}")
    @ResponseBody
    public JsonResult findByUid(@PathVariable("id") Integer id){
        CardCode id1 = cardCodeService.findId(id);
        JsonResult jsonResult = new JsonResult(JsonResult.SUCCESS,"查询成功",id1);
        return jsonResult;
    }
}
