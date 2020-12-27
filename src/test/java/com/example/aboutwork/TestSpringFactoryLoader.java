package com.example.aboutwork;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.config.DelegatingApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Component
@SpringBootTest
public class TestSpringFactoryLoader {

    ConfigurableApplicationContext context ;

    @Test
    public void  测试默认的ApplicationContextInitializer(){
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

        List<ApplicationListener> applicationListeners = SpringFactoriesLoader.loadFactories(ApplicationListener.class, defaultClassLoader);
        System.err.println(applicationListeners);
        	//[org.springframework.boot.devtools.restart.RestartApplicationListener@553a3d88,
               //org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor@7a30d1e6,
               //org.springframework.boot.context.config.ConfigFileApplicationListener@5891e32e,
               //org.springframework.boot.context.config.AnsiOutputApplicationListener@cb0ed20,
               //org.springframework.boot.context.logging.LoggingApplicationListener@8e24743,
               //org.springframework.boot.context.logging.ClasspathLoggingApplicationListener@74a10858,
               //org.springframework.boot.autoconfigure.BackgroundPreinitializer@23fe1d71,
               //org.springframework.boot.context.config.DelegatingApplicationListener@28ac3dc3,
               //org.springframework.boot.builder.ParentContextCloserApplicationListener@32eebfca,
               //org.springframework.boot.devtools.logger.DevToolsLogFactory$Listener@4e718207,
               //org.springframework.boot.ClearCachesApplicationListener@1d371b2d,
               //org.springframework.boot.context.FileEncodingApplicationListener@543c6f6d,
               //org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener@13eb8acf]


        List<SpringApplicationRunListener> springApplicationRunListeners = SpringFactoriesLoader.loadFactories(SpringApplicationRunListener.class, defaultClassLoader);
        System.err.println(springApplicationRunListeners);
    }




    @Test
    public void 测试SpringApplication默认的的listener(){
        Class<?>[] types = new Class<?>[] { SpringApplication.class, String[].class };
        Collection<String> listenerNames = getSpringFactoriesInstances(SpringApplicationRunListener.class, types);
        System.err.println(listenerNames); //[org.springframework.boot.context.event.EventPublishingRunListener]
    }

    @Test
    public void 测试两个存在继承关系class时_子类实际采用的泛型type(){
        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(DelegatingApplicationContextInitializer.class,
                ApplicationContextInitializer.class);
        //public class DelegatingApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {......}
        System.err.println(aClass.getName()); //org.springframework.context.ConfigurableApplicationContext
    }

    @Test
    public void 测试postProcessApplicationContext(){
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        factory.registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR,
                DefaultBeanNameGenerator.INSTANCE);
    }


    private <T> Collection<String> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        return new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
    }
}
