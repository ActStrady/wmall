package com.actstrady.wmall.po;

import com.actstrady.wmall.vo.ParentCategory;

public class CategoryConvert implements Converter<Category, ParentCategory> {
    @Override
    public ParentCategory Convert(Category input) {
        ParentCategory parent = new ParentCategory();
        parent.setId(input.getId());
        parent.setTitle(input.getTitle());
        return parent;
    }
}
