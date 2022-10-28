package com.cuiwei.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class MyBatisPlusConfig {

    @Bean//配置一个分页插件
    //配置一个拦截器
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
