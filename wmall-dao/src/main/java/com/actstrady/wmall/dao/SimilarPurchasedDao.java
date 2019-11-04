package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.SimilarPurchased;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 同类型推荐
 *
 * @author : ActStrady@tom.com
 * @date : 2019/11/2 17:23
 * @fileName : SimilarPurchased.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface SimilarPurchasedDao extends JpaRepository<SimilarPurchased, Integer> {
    List<SimilarPurchased> getByGoodsId(Integer goods);
}
