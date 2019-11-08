package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.Date;

/**
 * 评价列表
 */
@Data
public class EvaluateVO {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer goodsId;
    private Integer cartId;
    private Double grade;
    private String comment;
    private Date createTime;
}
