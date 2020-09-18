package com.example.aboutwork.controller;


import com.example.aboutwork.service.TxHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txManager/")
public class TxManagerController {

    @Autowired
    TxHandleService txService;

    @GetMapping("test")
    public Object testTxManager() {
        return txService.testTxManager();
    }

}
