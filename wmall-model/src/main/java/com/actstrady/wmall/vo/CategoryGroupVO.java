package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryGroupVO {
    private String groupName;
    private List<ChildCategoryVO> categories;
}
