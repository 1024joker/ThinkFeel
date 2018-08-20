package com.mxthd.dao;

import com.mxthd.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    public List<Goods> getAllGoods(Goods goods);
//    根据标题查商品
    public List<Goods> getGoodsByTitle(@Param("title") String title);
}
