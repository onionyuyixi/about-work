package com.example.aboutwork.castupper;

import org.junit.Test;

public class UpperImpl implements Upper,A {



    @Test
    public void 测试向父类cast(){
        UpperImpl upperImpl = new UpperImpl();
        if(upperImpl instanceof  Upper){
            Upper upper = upperImpl;
            upper.print();
        }
        if(upperImpl instanceof  A){
            A a = upperImpl;
            a.printA();
        }

    }

    @Override
    public void print() {
        System.err.println("upperImpl");
    }

    @Override
    public void printA() {
        System.err.println("upperImplA");
    }
}
