package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.service.GoodsService;
import com.actstrady.wmall.vo.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;
    private final CategoryDao categoryDao;

    @Autowired
    public GoodsServiceImpl(GoodsDao goodsDao, CategoryDao categoryDao) {
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
    }

    // 封装成页面展示的list
    private List<Goods4List> buildGoodsList(List<Goods> goods) {
        if (goods == null || goods.size() == 0) {
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for (Goods item : goods) {
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
        String slide = item.getSlidePicture();

        //处理华东图片
        slide = slide.substring(1, slide.length() - 1);
        String[] sp = slide.split("\\|");
        result.setSlide_1(sp[0].substring(1, sp[0].length() - 1));
        result.setSlide_2(sp[1].substring(1, sp[1].length() - 1));
        result.setSlide_3(sp[2].substring(1, sp[2].length() - 1));
        result.setSlide_4(sp[3].substring(1, sp[3].length() - 1));
        //处理detail图片
        if (item.getDetailPicture() != null) {
            String details = item.getDetailPicture();

            List<String> sList = new ArrayList<>();
            if (details.length() > 2) {
                details = details.substring(1, details.length() - 1);
                String[] sDetails = details.split("\\|");
                for (String s : sDetails) {
                    sList.add(s.substring(1, s.length() - 1));
                }
            }
            result.setDetailPicture(sList);
        }
        return result;
    }

    @Override
    public List<Goods4List> getAll(int pageSize, int pageIndex) {
        Page<Goods> goodsPage = goodsDao.findAll(PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsList(goodsPage.getContent());
    }

    @Override
    public List<Goods4List> getByCategory(int categoryId, int pageSize, int pageIndex) {
        Page<Goods> goodsPage = goodsDao.getByCategoryId(categoryId, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsList(goodsPage.getContent());
    }

    @Override
    public Goods4List getById(int goodsId) {
        return buildGoods(goodsDao.getOne(goodsId));
    }

    @Override
    public List<Goods4List> getByName(String goodsName, int pageSize, int pageIndex) {
        Page<Goods> goodsPage = goodsDao.getByGoodsName(goodsName, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsList(goodsPage.getContent());
    }

    @Override
    public void updateGradeById(int goodId, double addGrade) {
        Goods goods = goodsDao.getOne(goodId);
        int newRankNum = goods.getRankNum() + 1;
        double newGrade = Double.parseDouble(String.format("%.3f", ((goods.getRankNum() * goods.getGrade() + addGrade) / newRankNum)));
        goods.setRankNum(newRankNum);
        goods.setGrade(newGrade);
        // save也可以更新操作,当entity定义主键时
        goodsDao.save(goods);
    }

    @Override
    public List<Goods4List> getNewsByTime() {
        Page<Goods> goodsPage = goodsDao.findAll(PageRequest.of(0, 9, Sort.by("createTime").descending()));
        return buildGoodsList(goodsPage.getContent());
    }
}
