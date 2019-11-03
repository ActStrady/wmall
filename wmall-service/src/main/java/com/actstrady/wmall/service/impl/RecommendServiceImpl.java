package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.RecommendDao;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.Recommend;
import com.actstrady.wmall.service.RecommendService;
import com.actstrady.wmall.vo.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    private final GoodsDao goodsDao;
    private final RecommendDao recommendDao;
    private final CategoryDao categoryDao;

    @Autowired
    public RecommendServiceImpl(GoodsDao goodsDao, RecommendDao recommendDao, CategoryDao categoryDao) {
        this.goodsDao = goodsDao;
        this.recommendDao = recommendDao;
        this.categoryDao = categoryDao;
    }

    private List<Goods4List> buildRecommendGoodsList(List<Recommend> recomendGoods) {
        if (recomendGoods == null || recomendGoods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for (Recommend good : recomendGoods) {
            Goods item = goodsDao.getById(good.getGoodsId());
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
        result.setCategory(categoryMapper.getCategoryById(item.getCategoryId()));
        result.setCategoryId(item.getCategoryId());

        return result;
    }

    @Override
    public List<Goods4List> getByUserId(int userId) {
        return buildRecommendGoodsList(RecommendDao(userId));
    }
}
