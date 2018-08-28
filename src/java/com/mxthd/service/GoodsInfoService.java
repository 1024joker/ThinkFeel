package com.mxthd.service;

import com.mxthd.bean.Goods;
import com.mxthd.dao.GoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoService {
    @Autowired
    GoodsInfoMapper goodsInfoMapper;
    /*
    * 查询所有员工（可以接条件）
    * */
    public List<Goods> getGoodsInfo(Goods goods){
        return goodsInfoMapper.getAllGoods(goods);
    }
    /*
    * 根据标题查询
    * */
    public List<Goods> getGoodsByTitle(String title){
        return goodsInfoMapper.getGoodsByTitle(title);
    }
    /*
     * 首页查询
     * */
    public List<Goods> getHome(Integer t,Integer min,Integer max){
        return goodsInfoMapper.getHome(t,min,max);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Goods getById(Integer id){
        return goodsInfoMapper.getById(id);
    }

    /**
     * 用户访问 click+1
     * @param id
     */
    public void click(Integer id){

        goodsInfoMapper.click(id);
    }

    /*增加一个商品*/
    public Integer addGoods(Goods goods) {

        return goodsInfoMapper.addGoods(goods);
    }

    /*根据id修改商品信息*/
    public Integer updateById(Goods goods,Integer editId) {
        return goodsInfoMapper.updateGoods(goods,editId);
    }
}
