package com.cuiwei;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.cuiwei.mapper.UserMapper;
import com.cuiwei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatisPlus {
        @Test
        public void findAll() throws  Exception{
            String config = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(config);
            SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            //测试查询
            List<User> users = mapper.selectList(null);
            for (User user: users) {
                System.out.println(user);
            }
        }
    }
