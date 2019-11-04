package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 数据表Goods映射类
 */
@Data
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String goodsName;
    private String goodsIntroduce;
    private Double goodsPrice;
    private Integer categoryId;
    private String url;
    private String slidePicture;
    private Double grade;
    private Integer rankNum;
    private String detailPicture;
}