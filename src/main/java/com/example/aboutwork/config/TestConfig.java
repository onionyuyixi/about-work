package com.example.aboutwork.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 测试proxyBeanMethods的功效 作用
 */
@Configuration(proxyBeanMethods = false)
public class TestConfig {


    @Bean
    public Object getObject() {
        return new Object();
    }


    final public Object findObject() {
        return getObject();
    }


}

