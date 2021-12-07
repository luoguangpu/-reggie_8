package com.itheima.controller;


import com.itheima.bean.R;
import com.itheima.bean.Employee;
import com.itheima.service.EmployeeService;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService es;
    @PostMapping("employee/login")
    //接受前端的账号密码，调用service进行判定是否与数据库相同，密码需要进行加密，返回结果
    public R login(@RequestBody Employee employee,HttpSession session){
        Employee login = es.login(employee);

        R r=null;
        if(login!=null) {
            R.success("登录成功");
            session.setAttribute("employee", login);
        }else{
            R.error("登录失败");
        }
           return r;
    }

    @PostMapping("employee/logout")
  public R logout(HttpSession session){
        session.invalidate();

        return  R.success("已退出登录");


    }
}
