package com.example.baffledemo.controller;

import com.example.baffledemo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @PostMapping("/fpcheck")
    public String fpCheck(String fphm, String kpje) {
        return invoiceServiceImpl.checkInvoices(fphm, kpje);
    }
}
