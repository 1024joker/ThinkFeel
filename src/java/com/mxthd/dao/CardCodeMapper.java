package com.mxthd.dao;

public interface CardCodeMapper {
    //根据商品查询还有多少卡密
    Integer findCountByGood(Integer gid);
}
