package com.mxthd.bean;
/*
*   卡密表
* */
public class CardCode {
    private Integer id; //卡密id
    private Integer goodsId; //商品id
    private String code; //卡密
    private Integer state;//卡密状态（0可用  1不可用）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
