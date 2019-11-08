package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.PreferPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/11/3 20:26
 * @fileName : PreferDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface PreferDao extends JpaRepository<PreferPO, Integer> {
    List<PreferPO> getByUserId(Integer userId);
}
