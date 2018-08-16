package com.mxthd.bean;

import java.util.List;

/*
* 商品分类表(goodsclass)：
* */
public class GoodsClass {
    private Integer id;     //商品id
    private String name;    //商品分类名称
    List<Goods> goods;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
