package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.service.GoodsService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.GoodsVO;
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
    private final ListCopy<GoodsPO, GoodsVO> listCopy;

    public GoodsServiceImpl(GoodsDao goodsDao, CategoryDao categoryDao, ListCopy<GoodsPO, GoodsVO> listCopy) {
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
        this.listCopy = listCopy;
    }

    @Override
    public List<GoodsVO> getAll(int pageSize, int pageIndex) {
        Page<GoodsPO> goodsPage = goodsDao.findAll(PageRequest.of(pageIndex * pageSize, pageSize));
        List<GoodsVO> goodsVos = listCopy.listBuild(goodsPage.getContent(), GoodsVO.class);
        for (GoodsVO goodsVo : goodsVos) {
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
        }
        return goodsVos;
    }

    @Override
    public List<GoodsVO> getByCategory(int categoryId, int pageSize, int pageIndex) {
        Page<GoodsPO> goodsPage = goodsDao.getByCategoryId(categoryId, PageRequest.of(pageIndex * pageSize, pageSize));
        List<GoodsVO> goodsVos = listCopy.listBuild(goodsPage.getContent(), GoodsVO.class);
        for (GoodsVO goodsVo : goodsVos) {
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
        }
        return goodsVos;
    }

    @Override
    public GoodsVO getById(int goodsId) {
        GoodsPO goodsPo = goodsDao.getOne(goodsId);
        GoodsVO goodsVo = listCopy.beanBuild(goodsPo, GoodsVO.class);
        goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
        String slide = goodsPo.getSlidePicture();
        //处理滑动图片
        slide = slide.substring(1, slide.length() - 1);
        String[] sp = slide.split("\\|");
        goodsVo.setSlide_1(sp[0].substring(1, sp[0].length() - 1));
        goodsVo.setSlide_2(sp[1].substring(1, sp[1].length() - 1));
        goodsVo.setSlide_3(sp[2].substring(1, sp[2].length() - 1));
        goodsVo.setSlide_4(sp[3].substring(1, sp[3].length() - 1));
        //处理detail图片
        if (goodsVo.getDetailPicture() != null) {
            String details = goodsPo.getDetailPicture();
            List<String> sList = new ArrayList<>();
            if (details.length() > 2) {
                details = details.substring(1, details.length() - 1);
                String[] sDetails = details.split("\\|");
                for (String s : sDetails) {
                    sList.add(s.substring(1, s.length() - 1));
                }
            }
            goodsVo.setDetailPicture(sList);
        }
        return goodsVo;
    }

    @Override
    public List<GoodsVO> getByName(String goodsName, int pageSize, int pageIndex) {
        Page<GoodsPO> goodsPage = goodsDao.getByGoodsNameLike(goodsName, PageRequest.of(pageIndex * pageSize, pageSize));
        List<GoodsVO> goodsVos = listCopy.listBuild(goodsPage.getContent(), GoodsVO.class);
        for (GoodsVO goodsVo : goodsVos) {
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
        }
        return goodsVos;
    }

    @Override
    public void updateGradeById(int goodId, double addGrade) {
        GoodsPO goods = goodsDao.getOne(goodId);
        int newRankNum = goods.getRankNum() + 1;
        // 评价分算法
        double newGrade = Double.parseDouble(String.format("%.3f", ((goods.getRankNum() * goods.getGrade() + addGrade) / newRankNum)));
        goods.setRankNum(newRankNum);
        goods.setGrade(newGrade);
        // save也可以更新操作,当entity定义主键时
        goodsDao.save(goods);
    }

    @Override
    public List<GoodsVO> getNewsByTime() {
        Page<GoodsPO> goodsPage = goodsDao.findAll(PageRequest.of(0, 9, Sort.by("createTime").descending()));
        List<GoodsVO> goodsVos = listCopy.listBuild(goodsPage.getContent(), GoodsVO.class);
        for (GoodsVO goodsVo : goodsVos) {
            goodsVo.setCategory(categoryDao.getOne(goodsVo.getCategoryId()));
        }
        return goodsVos;
    }
}
