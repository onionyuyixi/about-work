package com.example.aboutwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@SpringBootApplication
public class AboutWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutWorkApplication.class, args);
    }

}
