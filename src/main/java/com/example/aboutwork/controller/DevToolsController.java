package com.example.aboutwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devTools/")
public class DevToolsController {

    @GetMapping("test")
    public Object test(){
        return "testonion-debug-kkk";
    }
}
