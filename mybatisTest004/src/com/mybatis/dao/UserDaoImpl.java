package com.mybatis.dao;

import com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//因为SqlSession是线程不安全的类所以不能放在全局变量上面，需要放在方法体中

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

//    public static void main(String[] args) throws Exception {
//        String resource = "SqlMapConfig.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
//        User user = userDao.findUserById(22);
//        System.out.println(user);
//    }

    @Override
    public void addUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectOne("test.addUser", user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void updateUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectOne("test.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteUser(String userName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectOne("test.deleteUser", userName);
        sqlSession.commit();
        sqlSession.close();
    }
}
