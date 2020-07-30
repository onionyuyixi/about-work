package com.example.aboutwork.proxy.service.impl;

import com.example.aboutwork.proxy.MyTransaction;
import com.example.aboutwork.proxy.service.BookService;

public class BookServiceImpl implements BookService {

    @Override
    @MyTransaction
    public void addBook() {
        query();
        query1();
        System.out.println("增加图书方法。。。");
    }

    @Override
    @MyTransaction
    public void query() {
        System.out.println("查询是否可以增加图书");
    }

    @MyTransaction
    void query1() {
        System.out.println("查询是否可以增加图书---private");
    }



}
