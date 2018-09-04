package com.mxthd.dao;

import com.mxthd.bean.CardCode;

import java.util.List;

public interface CardCodeMapper {
    //根据商品查询还有多少卡密
    Integer findCountByGood(Integer gid);
    //根据商品id查询一个可用的卡密
    CardCode findByGidStateOK(int gid);
    //修改卡密状态 为以使用
    void updateState(int id);
    CardCode findId(Integer id);
    /*获得卡密列表*/
    List<CardCode> getCardCode();
    /*新增卡密*/
    int addCardCode(CardCode cardCode);
}
