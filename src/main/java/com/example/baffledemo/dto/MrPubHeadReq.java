package com.example.baffledemo.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotEmpty;

/**
 *  贸融公共报文头
 *  ps:贸融请求报文为全小写
 *
 * @author sk
 * @date 2021-03-22
 */
public class MrPubHeadReq {

    /**
     * 交易流水号
     */
    @NotEmpty(message="交易流水号不能为空")
    @JSONField(name = "reqno")
    private String reqno;

    /**
     * 请求时间/响应时间
     */
    @NotEmpty(message="请求时间不能为空")
    @JSONField(name = "reqtime")
    private String reqtime;

    /**
     * 银行编码
     */
    @NotEmpty(message="银行编码不能为空")
    @JSONField(name = "bankid")
    private String bankid;

    /**
     * 接口编码
     */
    @NotEmpty(message="接口编码不能为空")
    @JSONField(name = "interid")
    private String interid;

    public String getReqno() {
        return reqno;
    }

    public void setReqno(String reqno) {
        this.reqno = reqno;
    }

    public String getReqtime() {
        return reqtime;
    }

    public void setReqtime(String reqtime) {
        this.reqtime = reqtime;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid;
    }

    @Override
    public String toString() {
        return "MrPubHead{" +
                "reqno='" + reqno + '\'' +
                ", reqtime='" + reqtime + '\'' +
                ", bankid='" + bankid + '\'' +
                ", interid='" + interid + '\'' +
                '}';
    }
}
