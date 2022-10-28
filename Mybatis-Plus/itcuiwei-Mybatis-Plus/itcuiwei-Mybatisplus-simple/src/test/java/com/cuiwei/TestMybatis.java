package com.cuiwei;

import com.cuiwei.mapper.UserMapper;
import com.cuiwei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
        @Test
        public void findAll() throws  Exception{
            String config = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(config);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            //测试查询
            List<User> all = mapper.findAll();
            for (User user : all) {
                System.out.println(user);
            }
        }
    }
