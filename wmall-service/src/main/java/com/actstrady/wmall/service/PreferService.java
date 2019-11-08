package com.actstrady.wmall.service;

import com.actstrady.wmall.vo.PreferVO;

import java.util.List;

/**
 * 分类信息服务
 */
public interface PreferService {

    /**
     * 根据用户编号获取用户喜欢列表
     * @param userId 用户编号
     * @return 喜好列表
     */
    public List<PreferVO> getByUserId(int userId);

    /**
     * 插入一条用户喜欢数据
     * @param userId 用户编号
     * @param categoryId 父级分类编号
     * @return 喜好列表
     */
    public void insertInfo(int userId, int categoryId);

}
