package com.example.baffledemo.service;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-15 17:20
 */
public interface InvoiceService {

    /**
     * 发票验证
     * @param fphm 发票号码
     * @param kpje 发票金额
     * @return
     */
    String checkInvoices(String fphm, String kpje);

}
