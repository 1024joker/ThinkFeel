package com.mxthd.bean;
/*
商品表(goods)
*/
public class Goods {
    private Integer id; //商品id
    private Integer goodsclassId; //分类id
    private String title; //商品标题
    private String content; //礼品介绍
    private Float marketprice; //市场价格
    private Float creditprice; //所需积分
    private Integer stock; //库存
    private Integer exchangeCount; //兑换人次
    private Integer click; //浏览次数
    private Integer type; //是否能够重复兑换（0 表示不重复  1表示可重复）
    private Integer post;//是否上架（0 表示上架 1 表示未上架）
    private GoodsClass goodsClass;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public GoodsClass getGoodsClass() {
        return goodsClass;
    }

    public Integer getGoodsclassId() {
        return goodsclassId;
    }

    public void setGoodsclassId(Integer goodsclassId) {
        this.goodsclassId = goodsclassId;
    }

    public void setGoodsClass(GoodsClass goodsClass) {
        this.goodsClass = goodsClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Float marketprice) {
        this.marketprice = marketprice;
    }

    public Float getCreditprice() {
        return creditprice;
    }

    public void setCreditprice(Float creditprice) {
        this.creditprice = creditprice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsclassId=" + goodsclassId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", marketprice=" + marketprice +
                ", creditprice=" + creditprice +
                ", stock=" + stock +
                ", exchangeCount=" + exchangeCount +
                ", click=" + click +
                ", type=" + type +
                ", post=" + post +
                ", goodsClass=" + goodsClass +
                ", photo='" + photo + '\'' +
                '}';
    }
}
