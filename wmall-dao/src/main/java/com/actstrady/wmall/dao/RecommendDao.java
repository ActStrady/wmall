package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/11/1 21:09
 * @fileName : RecommendDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface RecommendDao extends JpaRepository<Recommend, Integer> {
    List<Recommend> getByUserId(Integer userId);
}
