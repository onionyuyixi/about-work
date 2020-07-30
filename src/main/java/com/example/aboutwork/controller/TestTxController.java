package com.example.aboutwork.controller;


import com.example.aboutwork.service.TxService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class TestTxController {


    @Autowired
    TxService txService;

    @GetMapping("/tx")
    public Object tx() throws Exception {
        txService.txA();
        return "pk";
    }


    @GetMapping("/tx1")
    public Object tx1() throws Exception {
        txService.txB();
        return "pk";
    }
}
