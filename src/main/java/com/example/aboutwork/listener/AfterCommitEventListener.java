package com.example.aboutwork.listener;


import com.example.aboutwork.event.AfterCommitEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Component
public class AfterCommitEventListener {


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(AfterCommitEvent event) {

        System.err.println("TransactionPhase.AFTER_COMMIT-------");

        System.err.println(event.getName());


    }

}
