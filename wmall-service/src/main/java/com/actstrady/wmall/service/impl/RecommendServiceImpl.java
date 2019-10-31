package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private CategoryMapper categoryMapper;



    private List<Goods4List> buildRecommendGoodsList(List<Recommend> recomendGoods){
        if(recomendGoods==null || recomendGoods.size()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for(Recommend good:recomendGoods){
            Goods item=goodsMapper.getById(good.getGoodsId());
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
    public List<Goods4List> getByUserId(int userId) {
        return buildRecommendGoodsList(recommendMapper.getByUserId(userId));
    }
}
