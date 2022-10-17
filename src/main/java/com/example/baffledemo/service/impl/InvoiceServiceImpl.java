package com.example.baffledemo.service.impl;

import com.example.baffledemo.annotation.Baffle;
import com.example.baffledemo.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-15 17:30
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Override
    @Baffle(enable = "${baffle.invoice.checkfp.enable}", className = "${baffle.invoice.checkfp.classname}")
    public String checkInvoices(String fphm, String kpje) {
        log.info("请求航信发票接口查验发票真伪");
        return "查验成功发票不一致";
    }
}
