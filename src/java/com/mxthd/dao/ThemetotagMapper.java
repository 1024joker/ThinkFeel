package com.mxthd.dao;

import com.mxthd.bean.Themetotag;
import org.apache.ibatis.annotations.Param;

public interface ThemetotagMapper {
    public void add(@Param("themetotag") Themetotag themetotag);
}
