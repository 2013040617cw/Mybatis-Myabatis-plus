package com.cuiwei;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuiwei.mapper.UserMapper;
import com.cuiwei.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setEmail("@111.com");
        user.setAge(30);
        user.setUserName("caocao");
        user.setName("曹操");
        user.setPassword("705040");
        int result = userMapper.insert(user);
        System.out.println("result=>" + result);


        System.out.println("最终的ID为=》" + user.getId());


    }

}
