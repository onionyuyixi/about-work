package com.example.aboutwork.service.impl;

import com.example.aboutwork.service.TxHandleService;
import com.example.aboutwork.txhandle.BeanSelfAware;
import com.example.aboutwork.txhandle.txmanager.MyTxSynchronization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;


@Component
public class TxHandleServiceImpl extends BeanSelfAware<TxHandleServiceImpl> implements TxHandleService {

    @Autowired
    MyTxSynchronization myTxSynchronization;


    @Override
    public void txA() {
        TxHandleService self = getSelf();
        self.txB();
        System.err.println(1);
    }

    @Override
    public void txB() {
        System.err.println(2);
    }

    @Override
    @Transactional
    public Object testTxManager() {
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        System.err.println("123--------->455465");
        myTxSynchronization.executeAfterTxCompletion(() -> System.err.println("executeAfterTxCompletion"));
        return "okay";
    }
}
