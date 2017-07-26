package com.mybatis.dao;

import com.mybatis.pojo.User;
import com.mybatis.pojo.UserQueryVo;

public interface UserDao {
    //根据用户id查询用户
    public User findUserById(int id) throws Exception;
    //添加用户
    public void addUser(User user) throws Exception;
    //根据id更新用户
    public void updateUser(User user) throws Exception;
    //根据id删除用户
    public void deleteUser(String userName) throws Exception;

}
