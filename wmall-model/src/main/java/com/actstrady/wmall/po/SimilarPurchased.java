package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据表SimilarPurchased映射类
 */
@Data
@Entity(name = "Similarpurchased")
public class SimilarPurchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goodsid")
    private Integer goodsId;
    @Column(name = "similargoodsid")
    private Integer similarGoodsId;
    @Column(name = "createtime")
    private Date createTime;
}
