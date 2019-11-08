package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 评价
 */
@Data
@Entity(name = "assess")
public class EvaluatePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "userid")
    private Integer userId;
    // 不映射此字段
    @Transient
    @Column(name = "username")
    private String userName;
    @Column(name = "goodsid")
    private Integer goodsId;
    @Column(name = "cartid")
    private Integer cartId;
    private Double grade;
    private String comment;
    @Column(name = "createtime")
    private Date createTime;
}
