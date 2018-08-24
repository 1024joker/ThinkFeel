package com.mxthd.dao;

import com.mxthd.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    public List<Goods> getAllGoods(Goods goods);
//    根据标题查商品
    public List<Goods> getGoodsByTitle(@Param("title") String title);
//    首页查询
    public List<Goods> getHome(@Param("t") Integer t,@Param("min") Integer min,@Param("max") Integer max);
//    根据ID查询
    public Goods getById(Integer id);
//    增加一个商品
    public Integer addGoods(Goods goods);
}
