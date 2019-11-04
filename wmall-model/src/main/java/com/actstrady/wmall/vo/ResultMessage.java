package com.actstrady.wmall.vo;

import lombok.Data;

@Data
public class ResultMessage {
    /**
     * 返回消息
     */
     private Boolean isSuccess;
     private String Message;
}
