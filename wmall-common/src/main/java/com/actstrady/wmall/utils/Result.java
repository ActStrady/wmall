package com.actstrady.wmall.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 返回到前端的消息封装，暂时简单的这样写
 *
 * @author : ActStrady@tom.com
 * @date : 2019/10/28 9:03
 * @fileName : Result.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Data
@Component
@NoArgsConstructor
public class Result<T> {
    // 是否成功 0 成功 1 失败
    private Short status;
    // 内容
    private T data;
    // 错误码
    private String code;
}
