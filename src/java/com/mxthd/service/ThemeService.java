package com.mxthd.service;

import com.mxthd.bean.Tag;
import com.mxthd.bean.Theme;
import com.mxthd.bean.Themetotag;
import com.mxthd.dao.TagMapper;
import com.mxthd.dao.ThemeMapper;
import com.mxthd.dao.ThemetotagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ThemeService {
    @Autowired
    ThemeMapper themeMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    ThemetotagMapper themetotagMapper;

    public List<Theme> findByTopid(Integer topicId){
        return themeMapper.findByTopid(topicId);
    }

    public void addTheme(Theme theme, Set<String> tag){
        //发布文章
        themeMapper.addTheme(theme);
        //标签关联
        Iterator<String> iterator = tag.iterator();
        while (iterator.hasNext()){
            String tagName = iterator.next();
            Tag tag1=null;
            if(null==(tag1=tagMapper.findByName(tagName))){
                tagMapper.addTag(tagName);
                tag1=tagMapper.findByName(tagName);
            }
            Themetotag themetotag = new Themetotag();
            themetotag.setTagId(tag1.getId());
            themetotag.setThemeId(theme.getId());
            themetotagMapper.add(themetotag);
        }

    }
    public Theme findByid(Integer id){
        Theme tid = themeMapper.findTid(id);
        return tid;
    }
    public List<Theme> findBytagId(Integer tagId){
        return themeMapper.findBytagId(tagId);
    }
    public void addClick(Integer id){
        themeMapper.addClick(id);
    }
}
