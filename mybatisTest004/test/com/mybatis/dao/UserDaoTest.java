package com.mybatis.dao;

import com.mybatis.pojo.User;
import com.mybatis.pojo.UserCustom;
import com.mybatis.pojo.UserQueryVo;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }
    @Ignore
    @Test
    public void findUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        userMapper.deleteUser("王hong");
        User user = userMapper.findUserById(20);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findUserListTest() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setPassWord("wangweiweiwei");
        userCustom.setUserName("王卫");
        userQueryVo.setUserCustom(userCustom);

        List<User> list = userMapper.findUserList(userQueryVo);
        assertEquals(9,list.size());
        System.out.println(list);

    }

}