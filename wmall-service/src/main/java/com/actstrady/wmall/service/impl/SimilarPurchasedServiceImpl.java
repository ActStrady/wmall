package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.SimilarPurchasedDao;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.SimilarPurchased;
import com.actstrady.wmall.service.SimilarPurchasedService;
import com.actstrady.wmall.vo.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimilarPurchasedServiceImpl implements SimilarPurchasedService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SimilarPurchasedDao similarPurchasedDao;
    @Autowired
    private CategoryDao categoryDao;


    private List<Goods4List> buildSimilarPurchasedList(List<SimilarPurchased> goods) {
        if (goods == null || goods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for (SimilarPurchased good : goods) {
            Goods item = goodsDao.getOne(good.getSimilarGoodsId());
            Goods4List g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private Goods4List buildGoods(Goods item) {
        Goods4List result = new Goods4List();
        result.setId(item.getId());
        result.setName(item.getGoodsName());
        result.setPrice(item.getGoodsPrice());
        result.setUrl(item.getUrl());
        result.setDescription(item.getGoodsIntroduce());
        result.setCategoryId(item.getCategoryId());
        result.setCategory(categoryDao.getOne(item.getCategoryId()));
        result.setCategoryId(item.getCategoryId());

        return result;
    }

    @Override
    public List<Goods4List> getByGoodsId(int goodsId) {
        return buildSimilarPurchasedList(similarPurchasedDao.getByGoodsId(goodsId));
    }
}
