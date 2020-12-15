package com.example.aboutwork.controller;


import com.example.aboutwork.distributelock.ZkDistributeLock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/zk/")
public class ZkDistributeLockController {


    @GetMapping("test1")
    public void test1(){

        IntStream.rangeClosed(1,100).forEach(a->{
            new ZkDistributeLock(C)
        });
    }
}
