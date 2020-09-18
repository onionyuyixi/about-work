package com.example.aboutwork.service.impl;

import com.example.aboutwork.service.TxManagerService;
import com.example.aboutwork.txhandle.model.User;
import com.example.aboutwork.txhandle.repository.UserRepository;
import com.example.aboutwork.txhandle.txmanager.MyTxSynchronization;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;


@Component
public class TxManagerServiceImpl implements TxManagerService {

    @Autowired
    MyTxSynchronization myTxSynchronization;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Object testTxManager() {
        ArrayList<User> users = Lists.newArrayList();
        for (long i = 0; i < 1000; i++) {
            User user = new User(i, "onion", 27);
            users.add(user);
        }
        userRepository.saveAll(users);
        myTxSynchronization.executeAfterTxCompletion(() -> System.err.println("executeAfterTxCompletion"));
        return "okay";
    }
}
