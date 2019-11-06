package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/31 11:49
 * @fileName : CategoryDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> getByParentIdNull();

    /**
     * nativeQuery = true 表示使用原生sql
     *
     * @param parentId 父id
     * @return
     */
    @Query(value = "select distinct `group` from category where parentid = ?1", nativeQuery = true)
    List<String> getGroupByParentId(Integer parentId);

    List<Category> getByParentIdAndGroup(Integer parentId, String group);
}
