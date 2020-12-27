package com.example.aboutwork;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AboutWorkApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AboutWorkApplication.class, args);
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        factory.registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR,
                DefaultBeanNameGenerator.INSTANCE);
    }

}
