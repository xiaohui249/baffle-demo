package com.example.baffledemo.dto;

import com.example.baffledemo.utils.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @description 查询还款结果
 * @AUTHER: sk
 * @DATE: 2021/3/20
 **/
@Data
public class QueryRepayResultReq implements Serializable {


    /** 平台编码 */
    private String channelNo;//取缓存的值

    /** 业务类型 */
    @NotEmpty(message="业务类型不能为空")
    @JsonProperty("busstype")
    private String bussType;

    /** 平台交易编码 */
    @JsonProperty("bizno")
    private String bussNo;

    @JsonIgnore
    public String validate() {
        StringBuffer stringBuffer = new StringBuffer();
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(this);
        if (validResult.hasErrors()) {
            stringBuffer.append(validResult.getErrors());
        }
        return stringBuffer.toString();
    }
}
