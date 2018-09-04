package com.mxthd.dao;

import com.mxthd.bean.Record;

import java.util.List;

/**
 * 消费记录
 */
public interface RecordMapper {
    public List<Record> findByUid(Integer user_id);
}
