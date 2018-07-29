package com.mxthd.dao;

import com.mxthd.bean.Topic;

public interface TopicMapper {
    Topic findBySlug(String slug);
}
