package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/31 17:31
 * @fileName : Goods.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface GoodsDao extends JpaRepository<Goods, Integer> {
    Page<Goods> getByCategoryId(Integer categoryId, Pageable pageable);

    Page<Goods> getByGoodsNameLike(String goodsName, Pageable pageable);
}
