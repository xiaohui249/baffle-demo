package com.example.baffledemo.service.baffleimpl;

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
 * @date: 2022-10-23 16:04
 */
@Service
public class BaffleBankServiceImpl implements BankService {

    private static final Logger log = LoggerFactory.getLogger(BaffleBankServiceImpl.class);

    @Override
    public Object loanResult(LoanResultQueryReq loanResultQueryReq) {
        MrPubHeadReq headReq = loanResultQueryReq.getHead();
        log.info("【挡板处理】本次请求，银行编码为：{}，接口编码为：{}", headReq.getBankid(), headReq.getInterid());

        MrPubBody mrPubBody = new MrPubBody();
        mrPubBody.setCode("000");
        mrPubBody.setMessage("放款结果查询成功！");
        return mrPubBody;
    }

    @Override
    public Object repayResult(RepayResultQueryReq repayResultQueryReq) {
        MrPubHeadReq headReq = repayResultQueryReq.getHead();
        log.info("【挡板处理】本次请求，银行编码为：{}，接口编码为：{}", headReq.getBankid(), headReq.getInterid());

        MrPubBody mrPubBody = new MrPubBody();
        mrPubBody.setCode("000");
        mrPubBody.setMessage("放款结果查询成功！");
        return mrPubBody;
    }
}
