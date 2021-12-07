package com.itheima;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 启动类
@Slf4j // 加上这个注解了之后，在这个类里面就可以使用 log对象，然后使用它的方法来打印各种级别的日志。
@SpringBootApplication
@ServletComponentScan

// 主要是用来扫描以前web阶段的注解 @WebServlet  @WebFilter...
public class ReggieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class , args);
        log.debug("项目启动了~！！~");
    }
}
