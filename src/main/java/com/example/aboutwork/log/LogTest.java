package com.example.aboutwork.log;

import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class LogTest {

    private static final Logger LogTest = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testError(){
        try {

        sum();
        }catch (Exception e){
            LogTest.error("data {} error",1,e);
        }
    }

    @Test
    public void testWarn(){
        try {

            sum();
        }catch (Exception e){
            LogTest.warn("data {} error",1,e);
        }
    }

    @Test
    public void testInfo(){
        try {

            sum();
        }catch (Exception e){
            LogTest.info("data {} error",1,e);
        }
    }

    @Test
    public void testInfo1(){
        try {

            sum();
        }catch (Exception e){
            LogTest.info("data {} error...",1,e);
            LogTest.info("data {} error   ",1,e);
        }
    }

    private void sum(){
        int a  = 1/0;
    }
}
