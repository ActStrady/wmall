package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.Date;

/**
 * 评价列表
 */
@Data
public class EvaluateList {
    private int id;
    private int userId;
    private String userName;
    private int goodsId;
    private int cartId;
    private double grade;
    private String comment;
    private Date createTime;
}
