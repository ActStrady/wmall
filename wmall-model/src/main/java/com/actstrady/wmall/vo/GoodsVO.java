package com.actstrady.wmall.vo;

import com.actstrady.wmall.po.CategoryPO;
import lombok.Data;

import java.util.List;

/**
 * 商品列表，用于在首页进行商品展示使用
 */
@Data
public class GoodsVO {
    private Integer id;
    private String goodsName;
    private Double goodsPrice;
    private String url;
    private String goodsIntroduce;
    private Integer categoryId;
    private String slide1;
    private String slide2;
    private String slide3;
    private String slide4;
    private CategoryPO category;
    private List<String> detailPicture;
}
