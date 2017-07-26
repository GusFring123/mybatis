package com.mybatis.first;

import com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class MybatisFirst {

    //根据用户id查询用户信息，返回一条结果记录


    public SqlSession getSqlSession() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession(true);
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public User findUserById(int id) throws  Exception{
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过SqlSession操作数据库
        //第一个参数：映射文件中statement的id，等于namespace + "." + statement的id
        //第二个参数：指定和映射文件中所匹配的paremeterType类型的参数
        //sqlSession.selectOne结果是与映射文件中锁匹配的resultType类型的对象
        User user = sqlSession.selectOne("findUserById",id);
        System.out.println(user.getUserName());
        sqlSession.close();
        return user;
    }

    public void addUser(User user) {
        int rows;
        SqlSession sqlSession = getSqlSession();
        rows = sqlSession.insert("test.addUser",user);
        System.out.println("affected rows = " + rows);
        System.out.println("我的id是哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈：" + user.getNumber());
        sqlSession.close();
    }

    public void deleteUser(String userName) {
        int rows;
        SqlSession sqlSession = getSqlSession();
        rows = sqlSession.delete("test.deleteUser",userName);
        System.out.println("yingxianghangshuwei " + rows);
        sqlSession.close();
    }

    public User fingUserByName(String userName) {
        SqlSession sqlSession = getSqlSession();
        User user = sqlSession.selectOne("test.fingUserByName" ,userName);
        System.out.println(user.getCreate_time());
        sqlSession.close();
        return user;
    }

    public List<User> finfUserByNameMuhu(String userName) {
        SqlSession sqlSession = getSqlSession();
        List<User> users = sqlSession.selectList("test.muhuchaxun",userName);
//        System.out.println(users);
        sqlSession.close();
        return users;
    }

    public void updateUserById(User user) {
        SqlSession sqlSession = getSqlSession();
        int rows;
        rows = sqlSession.update("test.updateUser", user);

        System.out.println("影响行数" + rows);
        sqlSession.close();

    }

    public ResultSet select(int id) {
        SqlSession sqlSession = getSqlSession();
        ResultSet resultSet = sqlSession.selectOne("test.secondSelect", id);
        System.out.println("jinlaile");
        sqlSession.close();
        return resultSet;
    }

    public static void main(String[] args) throws Exception {
        MybatisFirst mybatisFirst = new MybatisFirst();
//        User user = mybatisFirst.findUserById(1);
//        System.out.println(user.getPassWord());

        User user1 = new User();
        user1.setUserName("庄江");
        user1.setPassWord("123455");
        user1.setCreate_time(new Date());
//        mybatisFirst.addUser(user1);
//        mybatisFirst.deleteUser("yangxuhong");
//        List<User> users = mybatisFirst.finfUserByNameMuhu("伟");
//        System.out.println(user);
//        System.out.println(users);
        User user = new User();
        user.setId(35);
        user.setUserName("王hong");
        user.setPassWord("wangweiweiwei");
        mybatisFirst.updateUserById(user);
//
// ResultSet resultSet = mybatisFirst.select(16);
//        while (resultSet.next()) {
//            String userName = resultSet.getString(2);
//            String passWord = resultSet.getString(3);
//            System.out.println(userName + " " + passWord);
//        }

    }
}
