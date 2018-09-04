package com.mxthd.bean;

import java.util.Date;

/*
* 消费记录表
* */
public class Record {
    private Integer id; //消费记录id
    private Integer userId; //用户id
    private Integer goodsId; //商品id
    private String goodsTitle;//标题
    private Integer cardcodeId; //卡密id
    private Date createDate; //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCardcodeId() {
        return cardcodeId;
    }

    public void setCardcodeId(Integer cardcodeId) {
        this.cardcodeId = cardcodeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
