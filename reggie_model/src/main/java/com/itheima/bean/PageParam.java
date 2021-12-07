package com.itheima.bean;

import lombok.Data;

@Data
public class PageParam {
    private Integer page;
    private Integer pageSize;
    private String name;
}
