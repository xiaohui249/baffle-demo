package com.example.baffledemo.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  公共报文体
 *
 * @author sk
 * @date 2021-03-22
 */
public class MrPubBody {

    /**
     * 返回码
     */
    @JSONField(name = "code")
    private String code;

    /**
     * 信息
     */
    @JSONField(name = "message")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MrPubBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
