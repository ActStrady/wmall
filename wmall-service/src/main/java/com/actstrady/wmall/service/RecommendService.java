package com.actstrady.wmall.service;

import com.actstrady.wmall.vo.GoodsVO;

import java.util.List;

/**
 * 商品服务类
 */
public interface RecommendService {

    /**
     * 根据用户编号获取个人推荐信息
     * @param userId 用户编号
     * @return 商品列表
     */
    public List<GoodsVO> getByUserId(int userId);

}
