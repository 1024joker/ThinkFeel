package com.mxthd.service;

import com.mxthd.bean.Goods;
import com.mxthd.bean.GoodsClass;
import com.mxthd.dao.GoodsClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsClassService {
    @Autowired
    GoodsClassMapper goodsClassMapper;
    /*根据id 查询商品类型信息*/
    public GoodsClass getGoodsTypeById(Integer id){
        return goodsClassMapper.getAllGoodsType(id);
    }
    /*查询所有商品类型信息*/
    public List<GoodsClass> getAll(){
        return goodsClassMapper.getAll();
    }
    /*增加一个商品类型*/
    public Integer addGoodsType(GoodsClass goodsClass) {
        return goodsClassMapper.addGoodsType(goodsClass);
    }

    public Integer goodsEdit(GoodsClass goodsClass,Integer id) {
        return goodsClassMapper.goodsEdit(goodsClass,id);
    }
}
