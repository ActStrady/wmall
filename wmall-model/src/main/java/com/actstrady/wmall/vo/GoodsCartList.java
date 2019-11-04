package com.actstrady.wmall.vo;

import com.actstrady.wmall.po.Goods;
import lombok.Data;

import java.util.Date;
@Data
public class GoodsCartList {
    private int id;
    private int userId;
    private int number;
    private int goodsId;
    private Date createTime;
    private int status;
    private String statusDesc;
    private Goods goods;
    private int parentCategoryId;
    private int evaluateId;
}
