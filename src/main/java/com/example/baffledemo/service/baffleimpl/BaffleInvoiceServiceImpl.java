package com.example.baffledemo.service.baffleimpl;

import com.example.baffledemo.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-15 17:33
 */
@Service
public class BaffleInvoiceServiceImpl implements InvoiceService {
    private static final Logger log = LoggerFactory.getLogger(BaffleInvoiceServiceImpl.class);

    @Override
    public String checkInvoices(String fphm, String kpje) {
        log.info("请求航信发票【挡板】接口查验发票真伪");
        return "查验成功发票一致";
    }
}
