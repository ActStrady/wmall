package com.actstrady.wmall.vo;

import com.actstrady.wmall.po.GoodsPO;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsCartVO {
    private Integer id;
    private Integer userId;
    private Integer number;
    private Integer goodsId;
    private Date createTime;
    private Integer status;
    private String statusDesc;
    private GoodsPO goodsPO;
    private Integer parentCategoryId;
    private Integer evaluateId;
}
