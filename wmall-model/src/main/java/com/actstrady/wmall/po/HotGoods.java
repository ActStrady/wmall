package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据表hotGoods映射类
 */
@Data
@Entity(name = "hotgoods")
public class HotGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goodsid")
    private Integer goodsId;
    @Column(name = "createtime")
    private Date createTime;
}
