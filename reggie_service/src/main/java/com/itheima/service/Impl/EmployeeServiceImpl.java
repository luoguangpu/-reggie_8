package com.itheima.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.bean.Employee;
import com.itheima.bean.PageParam;
import com.itheima.dao.EmployeeDao;
import com.itheima.service.EmployeeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

//添加事务的注解，如果service的方法出现了异常，那么就回滚事务。
// 其实不加注解，数据也会跑到数据库去，是因为mybatisplus背后添加的时候使用的是自动提交的功能。
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    //1. 注入进来dao
    @Autowired
    private EmployeeDao dao;

    /**
     * 登录功能
     * @param employee
     * @return
     */
    @Override
    public Employee login(Employee employee) {

        SqlSessionFactory factory = null;



        //1. 创建lmbda条件对象
        LambdaQueryWrapper<Employee> qw = new LambdaQueryWrapper<>();

        //1.1 对明文密码进行加密
        String md5Password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        //2. 组装查询条件
        qw.eq(Employee::getUsername , employee.getUsername());
        qw.eq(Employee::getPassword , md5Password);

        //3. 执行查询
        return (Employee) dao.selectOne(qw);

    }

    @Override
    public int add(Employee employee) {
        int row = dao.insert(employee);
       // System.out.println("row===============================================" + row);
        //int a = 1 / 0 ;
        return row;
    }

    @Override
    public IPage<Employee> findPage(PageParam pageParam) {

        //0. 设置查询条件
        LambdaQueryWrapper<Employee> qw = new LambdaQueryWrapper<>();

        //设置模糊查询，如果有传递过来姓名的信息，那么就追加按照姓名来进行模糊查询，否则这个条件就不会追加进来。
        qw.like(pageParam.getName() != null , Employee::getName , pageParam.getName());

        //1. 设置查询第几页 ，每页查询多少条
        Page<Employee> p =new Page<Employee>(pageParam.getPage() , pageParam.getPageSize());

        //2. 执行分页查询
        return dao.selectPage(p , qw );
    }

    @Override
    public int update(Employee employee) {
        return dao.updateById(employee);
    }
}

