package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据表Prefer映射类
 */
@Data
@Entity(name = "prefer")
public class PreferPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "userid")
    private Integer userId;
    @Column(name = "categoryid")
    private Integer categoryId;
    @Column(name = "createtime")
    private Date createTime;
}