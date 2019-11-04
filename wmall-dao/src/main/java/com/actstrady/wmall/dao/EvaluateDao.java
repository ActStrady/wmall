package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 评价
 *
 * @author : ActStrady@tom.com
 * @date : 2019/11/2 16:27
 * @fileName : EvaluateDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface EvaluateDao extends JpaRepository<Evaluate, Integer> {
    // 联合查询
    @Query(value = "select a.id,a.userid,l.username,a.goodsid,a.cartid,a.grade,a.comment,a.createtime " +
            "from assess as a, login as l " +
            "where a.userid = l.userid and goodsid = ?1 " +
            "limit ?2, ?3", nativeQuery = true)
    Page<Evaluate> getByGoodsId(Integer goodsId, Pageable pageable);

    Evaluate getByCartId(Integer cartId);
}
