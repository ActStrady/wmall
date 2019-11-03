package com.actstrady.wmall.po;

import com.actstrady.wmall.utils.Converter;
import com.actstrady.wmall.vo.CategoryGroup;

public class CategoryGroupConvert implements Converter<Category, CategoryGroup> {
    @Override
    public CategoryGroup convert(Category input) {
        CategoryGroup group = new CategoryGroup();
        group.setGroupName(input.getGroup());
        return group;
    }
}
