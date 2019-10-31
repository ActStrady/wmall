package com.actstrady.wmall.vo;

public class ResultMessage {
    /**
     * 返回消息
     */
     private Boolean isSuccess;
     private String Message;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
