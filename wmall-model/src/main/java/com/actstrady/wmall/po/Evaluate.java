package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 评价
 */
@Data
@Entity(name = "asses")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    // 不映射此字段
    @Transient
    private String userName;
    private Integer goodsId;
    private Integer cartId;
    private Double grade;
    private String comment;
    private Date createTime;
}
