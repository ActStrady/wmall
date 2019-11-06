package com.actstrady.wmall.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 菜单分类
 */
@Data
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String group;
    @Column(name = "parentid")
    private Integer parentId;
}
