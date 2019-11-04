package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "goodscart")
public class GoodsCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="userid")
    private Integer userId;
    private Integer number;
    @Column(name="goodsid")
    private Integer goodsId;
    @Column(name="createtime")
    private Date createTime;
    private Integer status;
}
