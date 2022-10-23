package com.example.baffledemo.controller;

import com.example.baffledemo.dto.LoanResultQueryReq;
import com.example.baffledemo.dto.MrPubHeadReq;
import com.example.baffledemo.dto.RepayResultQueryReq;
import com.example.baffledemo.service.BankService;
import com.example.baffledemo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-15 17:10
 */
@RestController
public class HelloController {

    @Autowired
    private InvoiceService invoiceServiceImpl;

    @Autowired
    private BankService bankServiceImpl;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @PostMapping("/fpcheck")
    public String fpCheck(String fphm, String kpje) {
        return invoiceServiceImpl.checkInvoices(fphm, kpje);
    }

    @PostMapping("/bank/loadResult")
    public Object queryLoadResult(String bankId) {
        MrPubHeadReq mrPubHeadReq = new MrPubHeadReq();
        mrPubHeadReq.setReqno("1111111111111111111");
        mrPubHeadReq.setReqtime(LocalDateTime.now().format(formatter));
        mrPubHeadReq.setBankid(bankId);
        mrPubHeadReq.setInterid("LOAN_RESULT");

        LoanResultQueryReq loanResultQueryReq = new LoanResultQueryReq();
        loanResultQueryReq.setHead(mrPubHeadReq);

        return bankServiceImpl.loanResult(loanResultQueryReq);
    }

    @PostMapping("/bank/repayResult")
    public Object queryRepayResult(String bankId) {
        MrPubHeadReq mrPubHeadReq = new MrPubHeadReq();
        mrPubHeadReq.setReqno("222222222222222222");
        mrPubHeadReq.setReqtime(LocalDateTime.now().format(formatter));
        mrPubHeadReq.setBankid(bankId);
        mrPubHeadReq.setInterid("REPAY_RESULT");

        RepayResultQueryReq repayResultQueryReq = new RepayResultQueryReq();
        repayResultQueryReq.setHead(mrPubHeadReq);

        return bankServiceImpl.repayResult(repayResultQueryReq);
    }
}
