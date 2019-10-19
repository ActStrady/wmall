package com.actstrady.wmall.po;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity
 * @author : ActStrady@tom.com
 * @date : 2019/10/11 20:03
 * @fileName : UserPO.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Data
@RequiredArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    @Column(name = "is_delete")
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
