package com.example.aboutwork.clazz;

import org.junit.Test;

public class TestClazz {

    @Test
    public void test1(){
        System.err.println(TestClazz.class.getClassLoader());
    }
}
