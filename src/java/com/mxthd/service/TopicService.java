package com.mxthd.service;

import com.mxthd.bean.Topic;
import com.mxthd.dao.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicMapper topicMapper;

    public Topic findBySlug(String slug){
        Topic bySlug = topicMapper.findBySlug(slug);
        return bySlug;
    }
    public List<Topic> findAll(){
        return topicMapper.findAll();
    }
}
