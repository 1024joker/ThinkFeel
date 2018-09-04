package com.mxthd.dao;

import com.mxthd.bean.Goods;
import com.mxthd.bean.GoodsClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsClassMapper {
    public GoodsClass getAllGoodsType(Integer id);
    /*查询所有商品类型信息*/
    public List<GoodsClass> getAll();
    /*增加一个商品类型*/
    public Integer addGoodsType(GoodsClass goodsClass);
    /*修改商品类型*/
    public Integer goodsEdit(@Param("GoodsClass") GoodsClass goodsClass,@Param("id") Integer id);
    /*删除商品类型*/
    int goodsDel(@Param("id") Integer id);
}
