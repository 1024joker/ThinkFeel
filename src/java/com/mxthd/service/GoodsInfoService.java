package com.mxthd.service;

import com.mxthd.bean.Goods;
import com.mxthd.dao.GoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoService {
    @Autowired
    GoodsInfoMapper goodsInfoMapper;
    /*
    * 查询所有员工
    * */
    public List<Goods> getGoodsInfo(){
        return goodsInfoMapper.getAllGoods(null);
    }
}
