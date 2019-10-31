package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.HotGoods;
import com.actstrady.wmall.service.HotGoodsService;
import com.actstrady.wmall.vo.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotGoodsServiceImpl implements HotGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private HotGoodsMapper hotGoodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;



    private List<Goods4List> buildHotGoodsList(List<HotGoods> hotGoods){
        if(hotGoods==null || hotGoods.size()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for(HotGoods hotGood:hotGoods){
            Goods item=goodsMapper.getById(hotGood.getGoodsId());
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
        result.setCategory(categoryMapper.getCategoryById(item.getCategoryId()));
        result.setCategoryId(item.getCategoryId());

        return result;
    }

    @Override
    public List<Goods4List> getTop() {
        return buildHotGoodsList(hotGoodsMapper.getTop());
    }
}
