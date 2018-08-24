package com.mxthd.service;

import com.mxthd.bean.User;
import com.mxthd.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MailService mailService;

    /**
     * 按用户名查询
     */
    public User findByUsername(String username){
        List<User> byWhere = userMapper.findByUsername(username);
        return byWhere.size()>0?byWhere.get(0):null;
    }

    /**
     * 按邮箱查询
     */
    public User findByEmail(String email){
        List<User> byWhere = userMapper.findByEmail(email);
        return byWhere.size()>0?byWhere.get(0):null;
    }

    /**
     * 添加普通用户
     * @param user
     * @return
     */
    public int addUser(User user){
        userMapper.addUser(user);
        //将注册之前的邮件，改为当前用户的邮件,并执行新用户欢迎邮件
        try {
            mailService.updateIdByEmail(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.getId();
    }

    /**
     * 用户登陆
     * @return
     */
    public User loginCheck(String username,String password){
        return userMapper.loginCheck(username,password);
    }

    /**
     * 在后台查询所有用户的信息
     * */
    public List<User> findAllUser(User users){
        return userMapper.findAllUser(users);
    }
}
