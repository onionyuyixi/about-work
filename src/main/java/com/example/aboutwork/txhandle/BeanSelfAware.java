package com.example.aboutwork.txhandle;

public class BeanSelfAware {

    BeanSelfAware self;

    public void setSelf(Object target) {
        this.self = (BeanSelfAware) target;
    }

    public BeanSelfAware getSelf() {
        return self;
    }
}
