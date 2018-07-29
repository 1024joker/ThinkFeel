package com.mxthd.service;

import com.mxthd.bean.LoginLog;
import com.mxthd.dao.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;

    /**
     * 添加日志
     */
    public void addLog(LoginLog loginLog){
        loginLogMapper.addLog(loginLog);
    }
}
