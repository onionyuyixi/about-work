package com.example.aboutwork.jvm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/jvm/")
public class JvmController {


    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();

    @GetMapping("doLoop")
    public void doLoop() {
        while (true) {
            map.put(new Object(), new Object());
        }
    }


    public static void main(String[] args) {
        int x = Runtime.getRuntime().availableProcessors();
        System.err.println(x);
    }

}
