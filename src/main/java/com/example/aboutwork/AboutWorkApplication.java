package com.example.aboutwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AboutWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutWorkApplication.class, args);
    }

}
