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
    private String name;
    private Double price;
    private String url;
    private String description;
    private Integer categoryId;
    private String slide_1;
    private String slide_2;
    private String slide_3;
    private String slide_4;
    private CategoryPO category;
    private List<String> detailPicture;
}
