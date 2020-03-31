package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();

        for (User user1 : all) {
            System.out.println(user1);
        }
        System.out.println("++++++++++++++++++++++insert+++++++++++++++++++++++++++");
        User newuser = new User();
        newuser.setId(513812);
        newuser.setUsername("zhouyu");
        userDao.insert(newuser);
        all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        System.out.println("++++++++++++++++++++++update+++++++++++++++++++++++++++");
        newuser.setUsername("zhoulang");
        userDao.update(newuser);
        all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        System.out.println("++++++++++++++++++++++delete+++++++++++++++++++++++++++");
        userDao.delete(newuser);
        all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

    }


}
