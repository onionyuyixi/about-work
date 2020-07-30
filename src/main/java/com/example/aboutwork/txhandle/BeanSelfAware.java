package com.example.aboutwork.txhandle;

import lombok.Data;

@Data
public class BeanSelfAware<T extends BeanSelfAware> {
    T self;
}
