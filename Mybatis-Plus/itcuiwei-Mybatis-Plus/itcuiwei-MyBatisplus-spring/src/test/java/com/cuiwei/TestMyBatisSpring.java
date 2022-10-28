package com.cuiwei;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwei.mapper.UserMapper;
import com.cuiwei.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml")
public class TestMyBatisSpring {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }

    }


    //插入操作
    @Test
    public void testInsert() {
        User user = new User();
        user.setEmail("705040525@qq.com");
        user.setName("我是爸爸");
        user.setUserName("woshibaba");
        user.setAge(20);
        user.setPassword("88899999");
        userMapper.insert(user);
    }


    //更新操作（根据ID进行更新）
    @Test
    public void testUpdateID() {
        User user = new User();
        user.setId(1L);
        user.setEmail("888888@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("i==>" + i);

    }


    //更新操作（根据条件进行更新）
    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(20);
        user.setPassword("456123");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "zhangsan"); //匹配user_name = zhangsan的数据
        //根据条件进行更新
        int update = userMapper.update(user, wrapper);
        System.out.println("update" + update);
    }


    //更新操作（根据条件进行更新）
    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        //设置我们的更新字段
        wrapper.set("age", 21).set("password", "999999")
                //设置条件
                .eq("user_name", "wangwu");

        int update = userMapper.update(null, wrapper);
        System.out.println("update" + update);
    }


    //删除操作（根据ID进行删除）
    @Test
    public void DeleteID() {
        int i = userMapper.deleteById(5L);
        System.out.println("i====>" + i);
    }


    //删除操作（Map）
    @Test
    public void DeleteMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "456123");
        int i = userMapper.deleteByMap(map);
        System.out.println("i===>" + i);
    }


    ///删除操作
    @Test
    public void DeleteWrapper() {
        //方法一：
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name","zhaoliu").eq("password","123456");
//        int delete = userMapper.delete(wrapper);
//        System.out.println("i==>" + delete);

        User user = new User();
        user.setUserName("zhaoliu");
        user.setPassword("123456");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        userMapper.delete(wrapper);
    }


    //批量删除操作
    @Test
    public void deletepiliang() {

        int i = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println("i==>" + i);
    }


    //查询操作
    @Test
    public void SelectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }


    //查询操作（批量查询）
    @Test
    public void SelectBatchids() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        for (User user : users) {
            System.out.println(user);
        }
    }


    //查询操作（根据条件进行查询,仅能查询一条数据）
    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "guoshiqi");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }


    //查询操作（条件查询，且返回查询到的数据条数）
    @Test
    public void SelectAccount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询的条件就是年龄大于20的数据
        wrapper.gt("age", 20);
        //根据条件查询数据条数
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println("查询到的数据条数是：" + integer);
    }


    //查询操作
    @Test
    public void SelectList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("email", "@qq.com");
        List list = userMapper.selectList(wrapper);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    //分页查询
    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("email", "@qq.com"); //年龄大于20岁
        Page<User> page = new Page<>(1, 1);
        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("总页数： " + iPage.getPages());
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    @Test
    public void testAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.allEq(params,false);
//            List<User> users = userMapper.selectList(wrapper);
//            for (User user : users) {
//                System.out.println(user);
//            }
//        }
        QueryWrapper<User> wrapper1 = wrapper.allEq((s, o) -> (s.equals("age") || (s.equals("id"))), params);
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }




    //基本的比较操作
    @Test
    public void testEq(){
        QueryWrapper<User>   wrapper = new QueryWrapper<>();
        wrapper.eq("password","123456").gt("age",20);
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //模糊查询
    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ? //Parameters: %曹%(String)
        wrapper.like("age",2);
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //排序
    @Test
    public void OrderByAgeDESC(){
        QueryWrapper<User> wrapper= new QueryWrapper<>();
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //逻辑查询
    @Test
    public void testOR(){
        QueryWrapper<User> wrapper= new QueryWrapper<>();
        wrapper.eq("name","李慧糠").or().eq("age",21);
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //测试指定字段的查询
    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

//SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "李四")
                .or()
                .eq("age", 24)
                .select("id", "name", "age");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
