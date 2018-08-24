package com.mxthd.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mxthd.bean.Pay;
import com.mxthd.dao.PayMapper;
import com.mxthd.dao.UserMapper;
import com.mxthd.util.HttpUtils;
import com.mxthd.util.eapayUtils;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayService {
    @Autowired
    PayMapper payMapper;
    @Autowired
    UserMapper userMapper;

    public String orderAdd(Pay pay){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("appid", "1435484712");//应用的ID
        parameters.put("out_trade_no",pay.getPpid());//商户订单号
        parameters.put("total_fee",pay.getMoney().toString());//金额
        parameters.put("subject","[ThinkFeel]会员ID["+pay.getUid()+"]充值"+pay.getMoney()+"积分");//订单标题
        parameters.put("body","会员ID["+pay.getUid()+"]充值"+pay.getMoney()+"积分，积分充值不支持退款!");//订单m描述
        parameters.put("show_url","http://localhost:8080");//商品展示页
        parameters.put("sign",eapayUtils.signMd5(parameters));//商户签名
        String json = HttpUtils.sendPost("https://api.eapay.cc/v1/order/add", parameters);
        return json;
    }
    public void payAdd(Pay pay){
        payMapper.payAdd(pay);
    }
    public int payHuidiao(Integer id,Double total_fee,Integer uid){
        int result1 = payMapper.payHuidiao(id);
        int result2 = userMapper.Addcredit(uid, total_fee);
        return result1==1&&result2==1?1:0;
    }
    public Pay findByPPID(String ppid){
        return payMapper.findByPPID(ppid);
    }
    public List<Pay> findByUid(Integer uid){
        return payMapper.findByUid(uid);
    }
}
