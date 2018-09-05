package com.mxthd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxthd.bean.CardCode;
import com.mxthd.service.CardCodeService;
import com.mxthd.service.GoodsClassService;
import com.mxthd.util.JsonResult;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cardCode")
public class CardCodeController {
    @Autowired
    CardCodeService cardCodeService;

    /*查询所有卡密*/
    @RequestMapping("/getCardCode")
    @ResponseBody
    public JsonResult getCardCode(@RequestParam(value = "pn", defaultValue = "1")Integer pn) {
        PageHelper.startPage(pn, 7);
        List<CardCode> cardCodes = cardCodeService.getCardCode();
        PageInfo pageInfo = new PageInfo(cardCodes, 5);
        JsonResult json = new JsonResult(0, "cardCode", pageInfo);
        return json;
    }

    /*新增卡密*/
    @RequestMapping("/addCardCode")
    @ResponseBody
    public JsonResult addCardCode(CardCode cardCode){
        int i = cardCodeService.addCardCode(cardCode);
        return null;
    }
}
