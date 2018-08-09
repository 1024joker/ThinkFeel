package com.mxthd.dao;

import com.mxthd.bean.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper {
    public List<Comments> findBythemeId(Integer tid);
    public void add(@Param("content") String content, @Param("tid") Integer tid, @Param("uid") Integer uid);
}
