package com.mxthd.dao;

import com.mxthd.bean.LoginLog;

public interface LoginLogMapper {
    /**
     * 添加日志
     * @param loginLog
     */
    void addLog(LoginLog loginLog);
}
