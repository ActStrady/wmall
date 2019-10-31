package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity 表示这是一个需要orm的实体类,name表示映射表名
 * Table 用来设置表的属性，很多属性，设置name后会优先于entity设置
 *
 * @author : ActStrady@tom.com
 * @date : 2019/10/11 20:03
 * @fileName : UserPO.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Data
@Entity
public class User {
    /**
     * mysql 使用GenerationType.IDENTITY规则主键自增
     */
    @Id
    // mysql需要按照这个设置，不然会在数据库多创建张表，这个的意思就是主键自增方式
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    // insertable=false表示的是创建表时忽略这个字段就不会出现插入成空的情况了，后面update是一样的
    // TODO 这里是为了解决插入与更新出现默认字段出现null字段的问题，后面再去研究其他实现方式
    @Column(name = "is_delete", insertable = false, updatable = false)
    private Boolean delete;
    @Column(insertable = false)
    private Date createTime;
    @Column(insertable = false)
    private Date updateTime;
    private Date deleteTime;
}
