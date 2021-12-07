package com.itheima.exception;


import com.itheima.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/*
    这是整个项目的全局异常处理器。
        1. 定义类，类上打注解 @RestControllerAdvice ，
            看成是可以对所有的RestController进行增强（其实就是给他们处理异常）

        2. 定义方法，方法上打注解 @ExceptionHandler ，
            处理什么异常，就在注解里面写上异常的字节码

        3. 如果希望得到这个异常的详细信息，就在方法里面定义出来这个异常的参数
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理数据库约束异常
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R handleExecption(SQLIntegrityConstraintViolationException e){

        //打印异常信息
        e.printStackTrace();
        log.debug("handleExecption执行了~！~！");

        //1. 获取异常的信息  Duplicate entry 'aaa' for key 'idx_username'
        String message = e.getMessage();

        //2. 判断现在的错误是不是数据重复的错误
        if(message.contains("Duplicate entry")){
            //3. 截取是什么样的字段出现了重复
            String val = message.split(" ")[2];

            return R.error(val + " 已存在！");
        }

        return R.error("未知错误！");
    }

    /**
     * 处理自定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R handleCustomException(CustomException e){
        return R.error(e.getMessage());
    }


}
