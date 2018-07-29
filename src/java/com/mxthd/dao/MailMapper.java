package com.mxthd.dao;

import com.mxthd.bean.Mail;
import com.mxthd.bean.User;

public interface MailMapper {
    //添加邮件
    void addMail(Mail mail);
    //将为null的邮件修改成当前用户，一般是将注册之前的邮件
    int updateIdByEmail(User user);
}
