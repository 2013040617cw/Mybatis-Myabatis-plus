package com.cuiwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuiwei.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    public List<User> findAll();
}
