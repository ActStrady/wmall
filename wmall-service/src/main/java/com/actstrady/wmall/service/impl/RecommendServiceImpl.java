package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.RecommendDao;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.RecommendPO;
import com.actstrady.wmall.service.RecommendService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.GoodsVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    private final GoodsDao goodsDao;
    private final RecommendDao recommendDao;
    private final CategoryDao categoryDao;
    private final ListCopy<GoodsPO, GoodsVO> listCopy;

    public RecommendServiceImpl(GoodsDao goodsDao, RecommendDao recommendDao,
                                CategoryDao categoryDao, ListCopy<GoodsPO, GoodsVO> listCopy) {
        this.goodsDao = goodsDao;
        this.recommendDao = recommendDao;
        this.categoryDao = categoryDao;
        this.listCopy = listCopy;
    }

    private List<GoodsVO> buildRecommendGoodsList(List<RecommendPO> recommendationGoods) {
        if (recommendationGoods == null || recommendationGoods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<GoodsVO> result = new ArrayList<>();
        for (RecommendPO recommend : recommendationGoods) {
            GoodsPO goodsPo = goodsDao.getOne(recommend.getGoodsId());
            GoodsVO goodsVO = listCopy.beanBuild(goodsPo, GoodsVO.class);
            goodsVO.setCategory(categoryDao.getOne(goodsVO.getCategoryId()));
            result.add(goodsVO);
        }
        return result;
    }

    @Override
    public List<GoodsVO> getByUserId(int userId) {
        return buildRecommendGoodsList(recommendDao.getByUserId(userId));
    }
}
