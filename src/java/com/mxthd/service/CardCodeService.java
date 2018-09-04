package com.mxthd.service;

import com.mxthd.bean.CardCode;
import com.mxthd.dao.CardCodeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardCodeService {
    @Autowired
    CardCodeMapper cardCodeMapper;


    public int findCountByGood(Integer gid){
        int result = cardCodeMapper.findCountByGood(gid);
        return result;
    }
    public CardCode findId(Integer id){
        return cardCodeMapper.findId(id);
    }

    /*获得卡密列表*/
    public List<CardCode> getCardCode() {
        return cardCodeMapper.getCardCode();
    }

    /*新增卡密*/
    public int addCardCode(CardCode cardCode) {
        return cardCodeMapper.addCardCode(cardCode);
    }
}
