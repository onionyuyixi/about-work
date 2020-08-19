package com.example.aboutwork.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/async/")
public class AsyncController {

    final Map<String, Callable<String>> map = new ConcurrentHashMap(64);

    final Map<String, CompletableFuture<String>> stringCompletableFutureMap = new ConcurrentHashMap(64);

    final Object sync = new Object();


    @GetMapping("getDefer/{key}/{timeOut}")
    public Object getDeffer(@PathVariable("key") String key, @PathVariable("timeOut") long timeOut) {
        long now = System.currentTimeMillis();
        long deadLine = now;
        if (timeOut != 0) {
            deadLine = now + TimeUnit.MILLISECONDS.toMillis(timeOut);
        }
        while (true) {

            long current = System.currentTimeMillis();
            if (deadLine - current <= 0) {
                return "timeOut";
            }
            final Callable<String> stringCallable = map.get(key);
            if (Objects.nonNull(stringCallable)) {
                try {
                    String result = stringCallable.call();
                    System.err.println("成功-------->" + result);
                    map.remove(key);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @GetMapping("setDefer/{key}")
    public Object setDefer(@PathVariable("key") String key) {
        map.putIfAbsent(key, () -> "defer--->" + key);
        return map;
    }


    @GetMapping("getDefferCompletable/{key}/{timeOut}")
    public Object getDefferCompletable(@PathVariable("key") String key, @PathVariable("timeOut") long timeOut) {

        while (true) {
            final CompletableFuture<String> completableFuture = stringCompletableFutureMap.get(key);
            if (Objects.nonNull(completableFuture)) {
                try {
                    String result = completableFuture.get(timeOut, TimeUnit.MILLISECONDS);
                    System.err.println("成功-------->" + result);
                    map.remove(key);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @GetMapping("setDeferCompletable/{key}")
    public Object setDeferCompletable(@PathVariable("key") String key) {
        stringCompletableFutureMap.putIfAbsent(key, CompletableFuture.supplyAsync(() -> key));
        return stringCompletableFutureMap;
    }
}
