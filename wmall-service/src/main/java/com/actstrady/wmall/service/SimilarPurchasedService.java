package com.actstrady.wmall.service;

import com.actstrady.wmall.vo.Goods4List;

import java.util.List;

/**
 * 商品服务类
 */
public interface SimilarPurchasedService {

    /**
     * 根据商品编号获取商推荐购买商品信息
     * @param goodsId 商品编号
     * @return 商品列表
     */
    public List<Goods4List> getByGoodsId(int goodsId);

}
