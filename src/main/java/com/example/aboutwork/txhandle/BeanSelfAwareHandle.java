package com.example.aboutwork.txhandle;

import com.example.aboutwork.lazy.LazyTestInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class BeanSelfAwareHandle extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //开启lazy之后 应用减少了启动时间 （减少集中装载各种bean的时间） 改成了异步处理 这里可以提供很好的参考
        if (bean instanceof BeanSelfAware) {
            System.err.println("开始后处理");
            BeanSelfAware beanSelfAware = (BeanSelfAware) bean;
            beanSelfAware.setSelf((BeanSelfAware) bean);
            return beanSelfAware;
        }

        if (bean instanceof LazyTestInterface) {
            System.err.println("Lazy开始后处理");
            return bean;
        }
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
