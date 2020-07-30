package com.example.aboutwork.proxy;

import com.example.aboutwork.proxy.cglibproxy.CglibProxyUtil;
import com.example.aboutwork.proxy.service.impl.BookServiceImpl;

public class TestCglib {
    public static void main(String[] args) {

        CglibProxyUtil cglibProxyUtil = new CglibProxyUtil();

        BookServiceImpl bookServiceImpl = (BookServiceImpl) cglibProxyUtil.getInstance(new BookServiceImpl());

        bookServiceImpl.addBook();


    }
}
