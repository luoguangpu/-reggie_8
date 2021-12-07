package com.itheima.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.itheima.bean.Employee;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Configuration
public class MybatisPlusMetaObjectHandler  implements MetaObjectHandler {

    @Autowired
    private HttpSession session;

    //给那些已经标注了添加填充值的字段赋值
    @Override
    public void insertFill(MetaObject metaObject) {

        System.out.println("MybatisPlusMetaObjectHandler..insertFill===" + Thread.currentThread().getId());

        Employee em = (Employee) session.getAttribute("employee");
        System.out.println("em =============================== " + em);
        metaObject.setValue("createTime" , LocalDateTime.now());
        metaObject.setValue("updateTime" , LocalDateTime.now());
//        metaObject.setValue("createUser" , em.getId());
//        metaObject.setValue("updateUser" , em.getId());


        metaObject.setValue("createUser" , BaseContext.get());
        metaObject.setValue("updateUser" , BaseContext.get());
    }

    //给那些已经标注了更新操作，填充值的字段赋值
    @Override
    public void updateFill(MetaObject metaObject) {
        Employee em  = (Employee) session.getAttribute("employee");
        metaObject.setValue("updateTime" , LocalDateTime.now());
       // metaObject.setValue("updateUser" , em.getId());

        metaObject.setValue("updateUser" , BaseContext.get());
    }
}
