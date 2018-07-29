package com.mxthd.service;

import com.mxthd.bean.TO.TopicTo;
import com.mxthd.bean.Topic;
import com.mxthd.dao.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicMapper topicMapper;

    public TopicTo findBySlug(){

        return null;
    }
}
