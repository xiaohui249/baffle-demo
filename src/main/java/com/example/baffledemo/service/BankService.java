package com.example.baffledemo.service;

import com.example.baffledemo.dto.LoanResultQueryReq;
import com.example.baffledemo.dto.RepayResultQueryReq;
import org.springframework.stereotype.Service;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-23 14:47
 */
@Service
public interface BankService {

    Object loanResult(LoanResultQueryReq loanResultQueryReq);

    Object repayResult(RepayResultQueryReq repayResultQueryReq);

}
