package com.mxthd.bean;
/*
* 商品分类表(goodsclass)：
* */
public class GoodsClass {
    private Integer id;     //商品id
    private String name;    //商品分类名称
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
