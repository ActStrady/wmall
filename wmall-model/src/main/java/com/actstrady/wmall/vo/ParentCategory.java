package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.List;

/**
 * 对应页面中的父级分类
 */
@Data
public class ParentCategory{
    private int id;
    private String title;
    private List<CategoryGroup> groups;
}
