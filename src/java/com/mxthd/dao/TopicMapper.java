package com.mxthd.dao;

import com.mxthd.bean.Topic;

import java.util.List;

public interface TopicMapper {
    Topic findBySlug(String slug);
    List<Topic> findAll();
}
