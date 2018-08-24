package com.mxthd.dao;

import com.mxthd.bean.Pay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayMapper {
    public void payAdd(@Param("pay") Pay pay);
    public int payHuidiao(Integer id);
    public Pay findByPPID(String ppid);
    public List<Pay> findByUid(Integer uid);
}
