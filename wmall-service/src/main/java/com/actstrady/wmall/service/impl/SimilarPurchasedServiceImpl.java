package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.SimilarPurchasedDao;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.SimilarPurchasedPO;
import com.actstrady.wmall.service.SimilarPurchasedService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.GoodsVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimilarPurchasedServiceImpl implements SimilarPurchasedService {
    private final GoodsDao goodsDao;
    private final SimilarPurchasedDao similarPurchasedDao;
    private final CategoryDao categoryDao;
    private final ListCopy<GoodsPO, GoodsVO> listCopy;

    public SimilarPurchasedServiceImpl(GoodsDao goodsDao, SimilarPurchasedDao similarPurchasedDao, CategoryDao categoryDao, ListCopy<GoodsPO, GoodsVO> listCopy) {
        this.goodsDao = goodsDao;
        this.similarPurchasedDao = similarPurchasedDao;
        this.categoryDao = categoryDao;
        this.listCopy = listCopy;
    }


    private List<GoodsVO> buildSimilarPurchasedList(List<SimilarPurchasedPO> similarPurchaseds) {
        if (similarPurchaseds == null || similarPurchaseds.size() == 0) {
            return new ArrayList<>(0);
        }
        List<GoodsVO> result = new ArrayList<>();
        for (SimilarPurchasedPO similarPurchased : similarPurchaseds) {
            GoodsPO goodsPo = goodsDao.getOne(similarPurchased.getSimilarGoodsId());
            GoodsVO goodsVo = listCopy.beanBuild(goodsPo, GoodsVO.class);
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
            result.add(goodsVo);
        }
        return result;
    }

    @Override
    public List<GoodsVO> getByGoodsId(int goodsId) {
        return buildSimilarPurchasedList(similarPurchasedDao.getByGoodsId(goodsId));
    }
}
