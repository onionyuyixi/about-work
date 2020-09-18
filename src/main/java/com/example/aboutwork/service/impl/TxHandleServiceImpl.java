//package com.example.aboutwork.service.impl;
//
//import com.example.aboutwork.service.TxHandleService;
//import com.example.aboutwork.txhandle.BeanSelfAware;
//import com.example.aboutwork.txhandle.model.User;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//
//@Component
//public class TxHandleServiceImpl extends BeanSelfAware<TxHandleServiceImpl> implements TxHandleService {
//
//
//    @Override
//    public void txA() {
//        TxHandleService self = getSelf();
//        self.txB();
//        System.err.println(1);
//    }
//
//    @Override
//    public void txB() {
//        System.err.println(2);
//    }
//
//}
