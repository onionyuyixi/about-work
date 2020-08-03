package com.example.aboutwork.txhandle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class BeanSelfAwareHandle extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanSelfAware) {
            System.err.println("开始后处理");
            BeanSelfAware beanSelfAware = (BeanSelfAware) bean;
            beanSelfAware.setSelf((BeanSelfAware) bean);
            return beanSelfAware;
        }
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
