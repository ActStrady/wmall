package com.actstrady.wmall.vo;

import com.actstrady.wmall.po.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsCartList {
    private Integer id;
    private Integer userId;
    private Integer number;
    private Integer goodsId;
    private Date createTime;
    private Integer status;
    private String statusDesc;
    private Goods goods;
    private Integer parentCategoryId;
    private Integer evaluateId;
}
