package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.RecommendDao;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.RecommendPO;
import com.actstrady.wmall.service.RecommendService;
import com.actstrady.wmall.vo.GoodsVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    private final GoodsDao goodsDao;
    private final RecommendDao recommendDao;
    private final CategoryDao categoryDao;

    public RecommendServiceImpl(GoodsDao goodsDao, RecommendDao recommendDao, CategoryDao categoryDao) {
        this.goodsDao = goodsDao;
        this.recommendDao = recommendDao;
        this.categoryDao = categoryDao;
    }

    private List<GoodsVO> buildRecommendGoodsList(List<RecommendPO> recomendGoods) {
        if (recomendGoods == null || recomendGoods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<GoodsVO> result = new ArrayList<>();
        for (RecommendPO good : recomendGoods) {
            GoodsPO item = goodsDao.getOne(good.getGoodsId());
            GoodsVO g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private GoodsVO buildGoods(GoodsPO item) {
        GoodsVO result = new GoodsVO();
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
    public List<GoodsVO> getByUserId(int userId) {
        return buildRecommendGoodsList(recommendDao.getByUserId(userId));
    }
}
