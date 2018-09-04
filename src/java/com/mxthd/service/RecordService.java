package com.mxthd.service;

import com.mxthd.bean.Record;
import com.mxthd.dao.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;

    public List<Record> findByUid(int uid){
       return recordMapper.findByUid(uid);
    }
}
