package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.dao.HotGoodsDao;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.HotGoods;
import com.actstrady.wmall.service.HotGoodsService;
import com.actstrady.wmall.vo.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotGoodsServiceImpl implements HotGoodsService {
    private final GoodsDao goodsDao;
    private final HotGoodsDao hotGoodsDao;
    private final CategoryDao categoryDao;

    @Autowired
    public HotGoodsServiceImpl(HotGoodsDao hotGoodsDao, GoodsDao goodsDao, CategoryDao categoryDao) {
        this.hotGoodsDao = hotGoodsDao;
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
    }


    private List<Goods4List> buildHotGoodsList(List<HotGoods> hotGoods){
        if(hotGoods==null || hotGoods.size()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for(HotGoods hotGood:hotGoods){
            Goods item = goodsDao.getOne(hotGood.getGoodsId());
            Goods4List g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private Goods4List buildGoods(Goods item){
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
    public List<Goods4List> getTop() {
        Page<HotGoods> hotGoodsPage = hotGoodsDao.findAll(PageRequest.of(0, 9, Sort.by("createTime").descending()));
        return buildHotGoodsList(hotGoodsPage.getContent());
    }
}
