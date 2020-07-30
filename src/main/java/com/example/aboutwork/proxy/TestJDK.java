package com.example.aboutwork.proxy;

import com.example.aboutwork.proxy.jdkproxy.JdkProxyUtil;
import com.example.aboutwork.proxy.service.BookService;
import com.example.aboutwork.proxy.service.impl.BookServiceImpl;

public class TestJDK {
    public static void main(String[] args) {
        JdkProxyUtil jdkProxyUtil = new JdkProxyUtil();
        BookService bookService = (BookService) jdkProxyUtil.bind(new BookServiceImpl());
        bookService.addBook();
    }
}
