package com.itheima.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    //告诉mybatisplus 当我们对employee 做添加操作的时候，请帮我们设置这个字段|属性的值
    //这样子的话，我们在准备数据的时候，就不需要去操心这个字段的值了，全部由mybatisplus来赋值。
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //当我们对employee 做添加 或者 更新操作的时候， 由mybatisplus来完成填充值的工作。
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
