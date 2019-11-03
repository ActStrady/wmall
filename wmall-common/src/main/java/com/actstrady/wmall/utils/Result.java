package com.actstrady.wmall.utils;

import lombok.Data;
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
public class Result {
    // 是否成功 0 成功 1 失败
    private Short status;
    // 内容 全部封装为字符串
    private String data;
    // 错误码
    private String code;
}
