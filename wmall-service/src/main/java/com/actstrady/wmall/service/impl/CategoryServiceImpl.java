package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.po.CategoryPO;
import com.actstrady.wmall.service.CategoryService;
import com.actstrady.wmall.vo.CategoryGroup;
import com.actstrady.wmall.vo.ChildCategory;
import com.actstrady.wmall.vo.ParentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<ParentCategory> getCategories() {
        List<CategoryPO> categories = categoryDao.getByParentIdNull();
        if (categories == null || categories.size() == 0) {
            return new ArrayList<>(0);
        }
        List<ParentCategory> result = new ArrayList<>();
        // 一级菜单
        for (CategoryPO categoryPO : categories) {
            ParentCategory item = new ParentCategory();
            item.setId(categoryPO.getId());
            item.setTitle(categoryPO.getTitle());
            item.setGroups(new ArrayList<>());
            result.add(item);
            List<String> groups = categoryDao.getGroupByParentId(item.getId());
            // 二级菜单
            for (String groupName : groups) {
                CategoryGroup group = new CategoryGroup();
                group.setGroupName(groupName);
                group.setCategories(new ArrayList<>());
                item.getGroups().add(group);
                // 三级菜单
                List<CategoryPO> children = categoryDao.getByParentIdAndGroup(item.getId(), group.getGroupName());
                for (CategoryPO childItem : children) {
                    ChildCategory child = new ChildCategory();
                    child.setId(childItem.getId());
                    child.setTitle(childItem.getTitle());
                    group.getCategories().add(child);
                }
            }
        }
        return result;
    }
}
