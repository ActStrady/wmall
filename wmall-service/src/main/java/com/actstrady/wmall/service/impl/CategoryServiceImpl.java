package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.po.CategoryPO;
import com.actstrady.wmall.service.CategoryService;
import com.actstrady.wmall.vo.CategoryGroupVO;
import com.actstrady.wmall.vo.ChildCategoryVO;
import com.actstrady.wmall.vo.ParentCategoryVO;
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
    public List<ParentCategoryVO> getCategories() {
        List<CategoryPO> categories = categoryDao.getByParentIdNull();
        if (categories == null || categories.size() == 0) {
            return new ArrayList<>(0);
        }
        List<ParentCategoryVO> result = new ArrayList<>();
        // 一级菜单
        for (CategoryPO categoryPO : categories) {
            ParentCategoryVO item = new ParentCategoryVO();
            item.setId(categoryPO.getId());
            item.setTitle(categoryPO.getTitle());
            item.setGroups(new ArrayList<>());
            result.add(item);
            List<String> groups = categoryDao.getGroupByParentId(item.getId());
            // 二级菜单
            for (String groupName : groups) {
                CategoryGroupVO group = new CategoryGroupVO();
                group.setGroupName(groupName);
                group.setCategories(new ArrayList<>());
                item.getGroups().add(group);
                // 三级菜单
                List<CategoryPO> children = categoryDao.getByParentIdAndGroup(item.getId(), group.getGroupName());
                for (CategoryPO childItem : children) {
                    ChildCategoryVO child = new ChildCategoryVO();
                    child.setId(childItem.getId());
                    child.setTitle(childItem.getTitle());
                    group.getCategories().add(child);
                }
            }
        }
        return result;
    }
}
