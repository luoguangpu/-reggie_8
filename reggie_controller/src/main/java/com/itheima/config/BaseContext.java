package com.itheima.config;

/*
    这是用来包装ThreadLocal的工具类，里面提供了往ThreadLocal里面存数据以及从ThreadLocal里面取数据的办法
 */
public class BaseContext {

    //1. 创建ThreadLocal对象
    private final static ThreadLocal<Long> tl = new ThreadLocal<>();

    //2. 提供一一个方法用来存数据到threadLocal对象身上
    public static void set(Long id){
        tl.set(id);
    }

    //3. 提供一个方法用来从ThreadLocal里面获取数据
    public static Long get(){
       return  tl.get();
    }

}
