package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.SimilarPurchasedDao;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.SimilarPurchased;
import com.actstrady.wmall.service.SimilarPurchasedService;
import com.actstrady.wmall.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SimilarPurchasedServiceImpl implements SimilarPurchasedService {
    private final GoodsDao goodsDao;
    private final SimilarPurchasedDao similarPurchasedDao;
    private final CategoryDao categoryDao;

    @Autowired
    public SimilarPurchasedServiceImpl(GoodsDao goodsDao, SimilarPurchasedDao similarPurchasedDao, CategoryDao categoryDao) {
        this.goodsDao = goodsDao;
        this.similarPurchasedDao = similarPurchasedDao;
        this.categoryDao = categoryDao;
    }


    private List<GoodsVO> buildSimilarPurchasedList(List<SimilarPurchased> goods) {
        if (goods == null || goods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<GoodsVO> result = new ArrayList<>();
        for (SimilarPurchased good : goods) {
            Goods item = goodsDao.getOne(good.getSimilarGoodsId());
            GoodsVO g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private GoodsVO buildGoods(Goods item) {
        GoodsVO result = new GoodsVO();
        result.setId(item.getId());
        result.setName(item.getGoodsName());
        result.setPrice(item.getGoodsPrice());
        result.setUrl(item.getUrl());
        result.setDescription(item.getGoodsIntroduce());
        result.setCategoryId(item.getCategoryId());
        result.setCategory(categoryDao.getOne(item.getCategoryId()));
        return result;
    }

    @Override
    public List<GoodsVO> getByGoodsId(int goodsId) {
        return buildSimilarPurchasedList(similarPurchasedDao.getByGoodsId(goodsId));
    }
}
