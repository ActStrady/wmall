package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.EvaluatePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 评价
 *
 * @author : ActStrady@tom.com
 * @date : 2019/11/2 16:27
 * @fileName : EvaluateDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface EvaluateDao extends JpaRepository<EvaluatePO, Integer> {
    Page<EvaluatePO> getByGoodsId(Integer goodsId, Pageable pageable);

    EvaluatePO getByCartId(Integer cartId);
}
