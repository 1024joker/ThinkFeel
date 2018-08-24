package com.mxthd.dao;

import com.mxthd.bean.Theme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeMapper {
    List<Theme> findByTopid(Integer topicId);
    void addTheme(@Param("theme") Theme theme);
    Theme findTid(Integer tid);
    List<Theme> findBytagId(Integer tagId);
    void addClick(Integer id);
    void addCommentCount(Integer id);
    //查询五条热评主题
    List<Theme> findRenPing();
    List<Theme> findAll();
}
