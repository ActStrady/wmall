package com.actstrady.wmall.service;


import com.actstrady.wmall.vo.GoodsVO;

import java.util.List;

/**
 * 商品服务类
 */
public interface GoodsService {
    /**
     * 获取商品信息
     *
     * @param pageSize  每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<GoodsVO> getAll(int pageSize, int pageIndex);

    /**
     * 通过分类查询
     *
     * @param categoryId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public List<GoodsVO> getByCategory(int categoryId, int pageSize, int pageIndex);

    /**
     * 根据商品编号获取商品信息
     *
     * @param goodsId 商品编号
     * @return 用于展示的商品列表
     */
    public GoodsVO getById(int goodsId);

    /**
     * 根据商品名称获取指定商品信息
     *
     * @param goodsName 商品编号
     * @param pageSize  每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<GoodsVO> getByName(String goodsName, int pageSize, int pageIndex);

    /**
     * 根据物品编号更新物品的评价人数和评分
     *
     * @param goodsId  商品编号
     * @param addGrade 分数
     * @return 商品信息
     */
    public void updateGradeById(int goodsId, double addGrade);

    /**
     * 获取新品推荐
     *
     * @return 用于展示的商品列表
     */
    public List<GoodsVO> getNewsByTime();

}
