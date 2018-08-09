package com.mxthd.service;

import com.mxthd.bean.Tag;
import com.mxthd.dao.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;


    public Tag getByName(String name){
        return tagMapper.findByName(name);
    }
}
