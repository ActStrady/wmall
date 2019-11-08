package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.GoodsCartPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/11/3 20:51
 * @fileName : GoodsCaoartDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface GoodsCartDao extends JpaRepository<GoodsCartPO, Integer> {
    Page<GoodsCartPO> getByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);

    List<GoodsCartPO> getByUserIdAndStatusAndGoodsId(Integer userId, Integer ststus, Integer goodsId);
}
