package com.mxthd.service;

import com.mxthd.bean.Options;
import com.mxthd.dao.OptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionsService {
    @Autowired
    OptionsMapper optionsMapper;

    public Options getByKey(String key){
        return optionsMapper.findByKey(key);
    }
}
