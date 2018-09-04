package com.mxthd.dao;

import com.mxthd.bean.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消费记录
 */
public interface RecordMapper {
    public List<Record> findByUid(Integer user_id);
    public void add(@Param("uid") Integer uid,@Param("gid")  Integer gid,@Param("gTitle")  String gTitle,@Param("Cid")  Integer Cid);
    //根据id查找卡密
    public Record findId(Integer id);
}
