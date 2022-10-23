package com.example.baffledemo.service.impl;

import com.example.baffledemo.annotation.BankBaffle;
import com.example.baffledemo.dto.LoanResultQueryReq;
import com.example.baffledemo.dto.MrPubBody;
import com.example.baffledemo.dto.MrPubHeadReq;
import com.example.baffledemo.dto.RepayResultQueryReq;
import com.example.baffledemo.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-23 15:06
 */
@Service
public class BankServiceImpl implements BankService {

    private static final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);

    @Override
    @BankBaffle(enable = "${baffle.bank.enable}",
            className = "${baffle.bank.classname}",
            bankIds = "${baffle.bank.method.load-result-ids}"
    )
    public Object loanResult(LoanResultQueryReq loanResultQueryReq) {
        MrPubHeadReq headReq = loanResultQueryReq.getHead();
        log.info("本次请求，银行编码为：{}，接口编码为：{}", headReq.getBankid(), headReq.getInterid());

        MrPubBody mrPubBody = new MrPubBody();
        mrPubBody.setCode("110");
        mrPubBody.setMessage("放款结果查询失败！");
        return mrPubBody;
    }

    @Override
    @BankBaffle(enable = "${baffle.bank.enable}",
            className = "${baffle.bank.classname}",
            bankIds = "${baffle.bank.method.repay-result-ids}"
    )
    public Object repayResult(RepayResultQueryReq repayResultQueryReq) {
        MrPubHeadReq headReq = repayResultQueryReq.getHead();
        log.info("本次请求，银行编码为：{}，接口编码为：{}", headReq.getBankid(), headReq.getInterid());

        MrPubBody mrPubBody = new MrPubBody();
        mrPubBody.setCode("110");
        mrPubBody.setMessage("还款结果查询失败！");
        return mrPubBody;
    }
}
