package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PreferList {
    private int id;
    private int userId;
    private int categoryId;
    private Date createTime;
}
