package com.itheima.filter;


import com.itheima.bean.Employee;
import com.itheima.config.BaseContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
    定义过滤器，用来过滤登录的功能
        1. 定义类，实现接口Filter
        2. 实现方法doFilter
        3. 类上打注解 @WebFilter ， 过滤地址 /*
        4. 默认情况下，springboot的环境是不认为这些过滤器的，需要在启动类上面打上注解@ServletComponentScan

        5. 步骤：
            5.1 获取当前的请求地址
            5.2 判定请求地址是否要处理，如果不需要处理，就直接放行
            5.3 如果属于要处理的地址，接着继续判断是否有登录
                5.3.1 如果没有登录，就直接重定向到登录页面
                5.3.2 如果已经登录了，那么就直接放行
 */

@WebFilter("/*")
@Slf4j
public class LoginFilter implements Filter {

//    private AntPathMatcher pm = new AntPathMatcher();

    //定义出来放行的规则
    /*String [] paths = {
            "/backend/page/login/login.html",
            "/employee/login",
            "/employee/logout",
            "/**.css", "/**.js","/**.png","/**.jpg", "/**plugins*" , "/**ico*"
    };
*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("来了一个请求了~！~~！~~！！~~！！Filter===" + Thread.currentThread().getId());

        //1. 获取当前的请求地址
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        System.out.println("现在访问的地址是：" + uri);

       //2. 判断地址是否要处理 ,如果访问的是登录页面，或者现在正在执行登录 |  退出登录操作，那么就放行它。
        if(uri.contains("login") || uri.contains("logout")
                || uri.endsWith(".css") || uri.endsWith(".js")
                || uri.endsWith(".png") || uri.endsWith(".jpg")
                || uri.contains("plugins") || uri.contains("ico")){
            filterChain.doFilter(servletRequest , servletResponse);
            return ; //表示后面的代码不要走了。
        }

        // 从规则里面遍历每一个规则出来，然后让pm去匹配我们现在的请求地址
        /*boolean flag = false;
        for (String p : paths) {
            if(flag = pm.match(p , uri)){
                break;
            }
        }
        if(flag ){
            filterChain.doFilter(servletRequest , servletResponse);
            return ; //表示后面的代码不要走了。
        }*/


        //3. 判定是否已经登录了？如果登录了，就放行。没有登录，那么就要去往登录页面。
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        if(employee != null){

            //把员工的id保存到ThreadLocal里面去
            BaseContext.set(employee.getId());

            filterChain.doFilter(servletRequest , servletResponse);
            return ; //表示后面的代码不要走了。
        }

        //4. 如果到了这里，就表示没有登录
        resp.sendRedirect("/backend/page/login/login.html");

    }

}
