package com.mxthd.dao;

import com.mxthd.bean.Goods;

import java.util.List;

public interface GoodsInfoMapper {
    public List<Goods> getAllGoods(Goods goods);
}
