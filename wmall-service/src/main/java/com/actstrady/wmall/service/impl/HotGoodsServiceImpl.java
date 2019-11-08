package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.HotGoodsDao;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.HotGoodsPO;
import com.actstrady.wmall.service.HotGoodsService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author actst
 */
@Service
public class HotGoodsServiceImpl implements HotGoodsService {
    private final GoodsDao goodsDao;
    private final HotGoodsDao hotGoodsDao;
    private final CategoryDao categoryDao;
    private final ListCopy<GoodsPO, GoodsVO> listCopy;

    @Autowired
    public HotGoodsServiceImpl(HotGoodsDao hotGoodsDao, GoodsDao goodsDao,
                               CategoryDao categoryDao, ListCopy<GoodsPO, GoodsVO> listCopy) {
        this.hotGoodsDao = hotGoodsDao;
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
        this.listCopy = listCopy;
    }

    private List<GoodsVO> buildHotGoodsList(List<HotGoodsPO> hotGoodPos) {
        if (hotGoodPos == null || hotGoodPos.size() == 0) {
            return new ArrayList<>(0);
        }
        List<GoodsVO> result = new ArrayList<>();
        for (HotGoodsPO hotGood : hotGoodPos) {
            GoodsPO goodsPo = goodsDao.getOne(hotGood.getGoodsId());
            System.out.println(goodsPo);
            GoodsVO goodsVo = listCopy.beanBuild(goodsPo, GoodsVO.class);
            System.out.println(goodsVo);
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
            result.add(goodsVo);
        }
        return result;
    }

    @Override
    public List<GoodsVO> getTop() {
        Page<HotGoodsPO> hotGoodsPage = hotGoodsDao.findAll(PageRequest.of(0, 9, Sort.by("createTime").descending()));
        return buildHotGoodsList(hotGoodsPage.getContent());
    }
}
