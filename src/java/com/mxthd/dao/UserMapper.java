package com.mxthd.dao;

import com.mxthd.bean.User;

import java.util.List;

public interface UserMapper {
    //按用户名查找
    List<User> findByUsername(String username);
    //按邮件查找
    List<User> findByEmail(String mail);
    //添加用户
    void addUser(User user);
    //用户登陆
    User loginCheck(String username,String password);
    //按id查询
    User findById(Integer id);
}
