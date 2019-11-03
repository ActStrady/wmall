package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 数据表SimilarPurchased映射类
 */
@Data
@Entity
public class SimilarPurchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int goodsId;
    private int similarGoodsId;
    private Date createTime;
}
