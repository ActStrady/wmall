package com.actstrady.wmall.vo;

import lombok.Data;

@Data
public class SearchVO {
    /**
     * 返回消息
     */
    private int pageIndex;
    private int pageSize;
    private String searchCondition;
}
