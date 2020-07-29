package com.example.aboutworker.service.impl;

import com.example.aboutworker.event.AfterCommitEvent;
import com.example.aboutworker.model.PdBundleItemSku;
import com.example.aboutworker.repository.PdBundleItemSkuRepository;
import com.example.aboutworker.service.TxService;
import com.example.aboutworker.util.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component("txServiceImpl")
public class TxServiceImpl implements TxService {

    @Autowired
    PdBundleItemSkuRepository repository;
    @Autowired
    ApplicationContext context;

    TxService txService;

    @PostConstruct
    public void postConstruct() {
        txService = context.getBean("txServiceImpl", TxService.class);
    }

    @Override
    @Transactional
    public void txA() {
        DebugUtils.showTransactionStatus("txA");
        PdBundleItemSku itemSku = new PdBundleItemSku();
        itemSku.setItemId(1223234L);
        itemSku.setSkuId(21347L);
        repository.save(itemSku);
        context.publishEvent(new AfterCommitEvent(itemSku,"txA"));
    }

    @Override
    public void txB() {
        PdBundleItemSku pdBundleItemSku = new PdBundleItemSku();
        DebugUtils.showTransactionStatus("txB");
        repository.save(pdBundleItemSku);
        txC();
    }

    public void txC() {
        PdBundleItemSku pdBundleItemSku = new PdBundleItemSku();
        repository.save(pdBundleItemSku);
        txD();
    }

    public void txD() {
        throw new RuntimeException("txD");
    }
}
