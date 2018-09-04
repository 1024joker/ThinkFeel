package com.mxthd.service;

import com.mxthd.dao.CardCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardCodeService {
    @Autowired
    CardCodeMapper cardCodeMapper;


    public int findCountByGood(Integer gid){
        int result = cardCodeMapper.findCountByGood(gid);
        return result;
    }
}
