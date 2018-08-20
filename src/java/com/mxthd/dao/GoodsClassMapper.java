package com.mxthd.dao;

import com.mxthd.bean.GoodsClass;

import java.util.List;

public interface GoodsClassMapper {
    public GoodsClass getAllGoodsType(Integer id);
    public List<GoodsClass> getAll();
}
