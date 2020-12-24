package com.example.aboutwork;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

@SpringBootTest
public class TestSpringFactoryLoader {


    @Test
    public void  测试ApplicationContextInitializer(){
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        List<ApplicationContextInitializer> applicationContextInitializers = SpringFactoriesLoader.loadFactories(ApplicationContextInitializer.class, defaultClassLoader);
        System.err.println(applicationContextInitializers);
        //[org.springframework.boot.context.config.DelegatingApplicationContextInitializer@26794848,
        // org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer@302552ec,
        // org.springframework.boot.context.ContextIdApplicationContextInitializer@3d285d7e,
        // org.springframework.boot.devtools.restart.RestartScopeInitializer@40005471,
        // org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer@2cd76f31,
        // org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer@367ffa75,
        // org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer@49438269,
        // org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener@ba2f4ec]

    }



}
