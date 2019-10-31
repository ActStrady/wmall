package com.actstrady.wmall.service;


import com.actstrady.wmall.vo.ParentCategory;

import java.util.List;

/**
 * 分类信息
 */
public interface CategoryService {
    /**
     * 获取所有的一级分类信息
     * @return 一级分类列表
     */
    public List<ParentCategory> getCategories();
}
