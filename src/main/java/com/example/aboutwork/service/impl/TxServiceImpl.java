//package com.example.aboutwork.service.impl;
//
//import com.example.aboutwork.event.AfterCommitEvent;
//import com.example.aboutwork.model.PdBundleItemSku;
//import com.example.aboutwork.repository.PdBundleItemSkuRepository;
//import com.example.aboutwork.service.TxService;
//import com.example.aboutwork.util.TxDebugUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//
//@Component("txServiceImpl")
//public class TxServiceImpl implements TxService {
//
//    @Autowired
//    PdBundleItemSkuRepository repository;
//    @Autowired
//    ApplicationContext context;
//
//    TxService txService;
//
//
//    @PostConstruct
//    public void postConstruct() {
//        txService = context.getBean("txServiceImpl", TxService.class);
//    }
//
//    @Override
//    @Transactional
//    public void txA() {
//        TxDebugUtils.showTransactionStatus("txA");
//        PdBundleItemSku itemSku = new PdBundleItemSku();
//        itemSku.setItemId(1223234L);
//        itemSku.setSkuId(21347L);
//        repository.save(itemSku);
//        context.publishEvent(new AfterCommitEvent(itemSku, "txA"));
//    }
//
//    @Override
//    public void txB() {
//        PdBundleItemSku pdBundleItemSku = new PdBundleItemSku();
//        TxDebugUtils.showTransactionStatus("txB");
//        repository.save(pdBundleItemSku);
//        txC();
//    }
//
//    public void txC() {
//        PdBundleItemSku pdBundleItemSku = new PdBundleItemSku();
//        repository.save(pdBundleItemSku);
//        txD();
//    }
//
//    public void txD() {
//        throw new RuntimeException("txD");
//    }
//}
