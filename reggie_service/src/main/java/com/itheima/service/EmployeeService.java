package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.bean.Employee;
import com.itheima.bean.PageParam;

public interface EmployeeService {

    Employee login(Employee employee);

    int add(Employee employee);

    IPage<Employee> findPage(PageParam pageParam);

    int update(Employee employee);
}
