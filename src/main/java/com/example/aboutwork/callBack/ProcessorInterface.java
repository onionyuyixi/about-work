package com.example.aboutwork.callBack;

public interface ProcessorInterface<T> {

    T beforeCall(T t);

    T afterCall(T t);
}
