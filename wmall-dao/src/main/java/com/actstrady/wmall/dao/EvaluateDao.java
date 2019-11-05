package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
    @Query(value = "select a.id, a.userid, u.username, a.goodsid, a.cartid, a.grade, a.comment, a.createtime " +
            "from assess as a, user as u " +
            "where a.userid = u.id and a.goodsid = ?1 " +
            "limit ?2, ?3",
            nativeQuery = true)
    List<Evaluate> getByGoodsId(Integer goodsId, Integer offset, Integer pageSize);

    Evaluate getByCartId(Integer cartId);
}
