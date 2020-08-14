package com.example.aboutwork.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TransferQueue;

@RestController
@RequestMapping("/transferQueue/")
public class TransferQueueController {


    @Autowired
    @Qualifier("transferQueueDemo")
    TransferQueue<Integer> transferQueue;

    @GetMapping("add")
    public Object add(@RequestParam("num") Integer num) {
        boolean add = transferQueue.add(num);
        return add;
    }


    @GetMapping("put")
    public Object put(@RequestParam("num") Integer num) throws InterruptedException {
        transferQueue.put(num);
        return "put";
    }


    @GetMapping("offer")
    public Object offer(@RequestParam("num") Integer num) {
        boolean offer = transferQueue.offer(num);
        return offer;
    }


    @GetMapping("tryTransfer")
    public Object tryTransfer(@RequestParam("num") Integer num) {
        boolean tryTransfer = transferQueue.tryTransfer(num);
        return tryTransfer;
    }


    @GetMapping("transfer")
    public Object transfer(@RequestParam("num") Integer num) throws InterruptedException {
        transferQueue.transfer(num);
        return "transfer";
    }


    @GetMapping("take")
    public Object take() throws InterruptedException {
        return transferQueue.take();
    }


    @GetMapping("poll")
    public Object poll() {
        return transferQueue.poll();
    }
}
