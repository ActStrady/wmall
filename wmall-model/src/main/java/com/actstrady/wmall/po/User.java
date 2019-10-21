package com.actstrady.wmall.po;

import lombok.Cleanup;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
@Entity
public class User {
    /**
     * mysql 使用GenerationType.IDENTITY规则主键自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    @Column(name = "is_delete", insertable = false)
    private Boolean delete;
    @Column(insertable = false)
    private Date createTime;
    @Column(insertable = false)
    private Date updateTime;
    private Date deleteTime;
}
