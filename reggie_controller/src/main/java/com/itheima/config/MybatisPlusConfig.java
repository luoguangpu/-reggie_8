package com.itheima.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    配置分页拦截器
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor addInterceptor(){

        //1. 创建MybatisPlusInterceptor 对象
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();

        //2. 追加分页的拦截器
        mpi.addInnerInterceptor(new PaginationInnerInterceptor());

        return mpi;
    }
}
