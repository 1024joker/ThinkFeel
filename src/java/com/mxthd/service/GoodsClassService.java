package com.mxthd.service;

import com.mxthd.bean.GoodsClass;
import com.mxthd.dao.GoodsClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsClassService {
    @Autowired
    GoodsClassMapper goodsClassMapper;

    public List<GoodsClass> getAll(){
        return goodsClassMapper.getAll();
    }
}
