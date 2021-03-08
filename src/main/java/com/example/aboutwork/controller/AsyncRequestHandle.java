package com.example.aboutwork.controller;


import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async/")
public class AsyncRequestHandle {


    @SneakyThrows
    @GetMapping("test1")
    public CompletableFuture<String> test1() {
        Thread.sleep(10);
        System.err.println(Thread.currentThread().getName());
        System.err.println(Thread.currentThread().getThreadGroup().activeCount());
        return CompletableFuture.supplyAsync(() -> String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
    }

    @SneakyThrows
    @GetMapping("test2")
    public Callable<String> test2() {
        return () -> {
            Thread.sleep(20);
            String name = Thread.currentThread().getName();
            System.err.println(name);
            return name;
        };
    }


}
