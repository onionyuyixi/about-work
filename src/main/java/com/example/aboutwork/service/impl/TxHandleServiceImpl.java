package com.example.aboutwork.service.impl;

import com.example.aboutwork.service.TxHandleService;
import com.example.aboutwork.txhandle.BeanSelfAware;
import org.springframework.stereotype.Component;


@Component
public class TxHandleServiceImpl extends BeanSelfAware implements TxHandleService {


    @Override
    public void txA() {
        TxHandleService self = (TxHandleService) getSelf();
        self.txB();
        System.err.println(1);
    }

    @Override
    public void txB() {
        System.err.println(2);
    }
}
