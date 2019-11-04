package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "recommendation")
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "userid")
    private Integer userId;
    @Column(name = "goodsid")
    private Integer goodsId;
    @Column(name="createtime")
    private Date createTime;
}
