package com.example.aboutwork.function;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface MyConsumer<T> extends BiConsumer<Collection<T>, T> {

    MyConsumer<String> strConsumer = (strs, i) -> {
        strs.add(i);
        strs.remove(i);
    };


}
