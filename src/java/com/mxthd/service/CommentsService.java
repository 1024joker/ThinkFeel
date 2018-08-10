package com.mxthd.service;

import com.mxthd.bean.Comments;
import com.mxthd.dao.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    ThemeService themeService;

    public List<Comments> findByTid(Integer tid){
        return commentsMapper.findBythemeId(tid);
    }
    
    public void add(String content,Integer tid,Integer uid){
        commentsMapper.add(content,tid,uid);
        //评论后给主题评论数+1
        themeService.addCommentCount(tid);
        //评论后  给主题者发送通知
    }
}
