package com.actstrady.wmall.po;

import com.actstrady.wmall.utils.Converter;
import com.actstrady.wmall.vo.ChildCategory;

public class ChildCategoryConvert implements Converter<Category, ChildCategory> {
    @Override
    public ChildCategory convert(Category input) {
        ChildCategory child = new ChildCategory();
        child.setId(input.getId());
        child.setTitle(input.getTitle());
        child.setGroup(input.getGroup());
        return child;
    }
}
