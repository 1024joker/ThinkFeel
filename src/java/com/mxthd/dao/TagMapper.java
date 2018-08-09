package com.mxthd.dao;

import com.mxthd.bean.Tag;

import java.util.List;

public interface TagMapper {
    Tag findByName(String name);
    void addTag(String name);
    List<Tag> findBythemeId(Integer themeId);
}
