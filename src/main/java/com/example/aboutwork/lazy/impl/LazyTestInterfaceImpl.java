package com.example.aboutwork.lazy.impl;

import com.example.aboutwork.lazy.LazyTestInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LazyTestInterfaceImpl implements LazyTestInterface {

    private boolean isLazy;
    @Override
    public Object getObject() {
        return "lazyTest";
    }
}
