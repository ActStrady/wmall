package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据表Goods映射类
 */
@Data
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goodsname")
    private String goodsName;
    @Column(name = "goodsintroduce")
    private String goodsIntroduce;
    @Column(name = "goodsprice")
    private Double goodsPrice;
    @Column(name = "categoryid")
    private Integer categoryId;
    private String url;
    @Column(name = "slidepicture")
    private String slidePicture;
    private Double grade;
    @Column(name = "ranknum")
    private Integer rankNum;
    @Column(name = "detailpicture")
    private String detailPicture;
    @Column(name = "createtime")
    private Date createTime;
}