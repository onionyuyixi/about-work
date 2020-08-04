package com.example.aboutwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lazy/")
public class LazyController {

//
//    @Autowired
//    LazyTestInterface lazyTestInterface;

    @Autowired
    ApplicationContext applicationContext;


    @GetMapping("test")
    public Object getLazy(){
        Object beforeInstants = applicationContext.getBean("lazyTestInterfaceImpl");

//        Map<String, LazyTestInterface> beforeInstants = applicationContext.getBeansOfType(LazyTestInterface.class);
//
//        System.err.println(beforeInstants.size());
//
//        System.err.println(lazyTestInterface.getObject());
//
//        Map<String, LazyTestInterface> afterInstants = applicationContext.getBeansOfType(LazyTestInterface.class);
//
//        System.err.println(afterInstants.size());

        return "okay";
    }



}
