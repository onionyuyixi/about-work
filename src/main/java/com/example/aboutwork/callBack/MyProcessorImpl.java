package com.example.aboutwork.callBack;


import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

public class MyProcessorImpl implements ProcessorInterface<UseMyProcessor> {

    @Override
    public UseMyProcessor beforeCall(UseMyProcessor useMyCallBack) {
        System.err.println("before call invoke ........");
        useMyCallBack.setBefore("before use ");
        String use = useMyCallBack.getUse();
        return useMyCallBack;
    }

    @SneakyThrows
    @Override
    public UseMyProcessor afterCall(UseMyProcessor useMyCallBack) {
        String use = useMyCallBack.getUse();
        if (StringUtils.isEmpty(use)){
            throw new Exception("UseMyCallBack 的 use方法 没有生效");
        }
        System.err.println("after call invoke ........");
        useMyCallBack.setAfter("after use");
        return useMyCallBack;
    }
}
