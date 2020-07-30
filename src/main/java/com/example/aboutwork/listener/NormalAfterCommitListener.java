//package com.example.aboutwork.listener;
//
//import com.example.aboutwork.event.AfterCommitEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.support.TransactionSynchronization;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
///**
// * 实际上可以参考
// * {@link org.springframework.transaction.event.ApplicationListenerMethodTransactionalAdapter} \
// */
//@Component
//public class NormalAfterCommitListener {
//
//
//    @EventListener
//    public void handleEvent(AfterCommitEvent event) {
//
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//            @Override
//            public void afterCompletion(int status) {
//                if (status == STATUS_COMMITTED) {
//                    System.err.println("事务已经提交");
//                } else if (status == STATUS_ROLLED_BACK) {
//                    System.err.println("事务回滚");
//                } else {
//                    System.err.println("事务状态不清楚 程序出错");
//                }
//            }
//        });
//    }
//}
