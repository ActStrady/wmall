package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.HotGoods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/11/1 17:22
 * @fileName : HotGoodsDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface HotGoodsDao extends JpaRepository<HotGoods, Integer> {
}
