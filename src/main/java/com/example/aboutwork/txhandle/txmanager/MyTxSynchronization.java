package com.example.aboutwork.txhandle.txmanager;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;


@Slf4j
@Component
public class MyTxSynchronization extends TransactionSynchronizationAdapter{


    private static final FastThreadLocal<FastThreadLocalThread> actions = new FastThreadLocal();

    public void executeAfterTxCompletion(Runnable runnable) {
        TransactionSynchronizationManager.registerSynchronization(this);
        FastThreadLocalThread fastThreadLocalThread = wrapRunnable(runnable);
        actions.set(fastThreadLocalThread);
    }


    private FastThreadLocalThread wrapRunnable(Runnable runnable) {
        Assert.notNull(runnable, "线程不能为空");
        return new FastThreadLocalThread(runnable);

    }

    @Override
    public void afterCommit() {
        FastThreadLocalThread thread = actions.getIfExists();
        if (thread != null) {
            thread.start();
        }
    }

    @Override
    public void afterCompletion(int status) {
        actions.remove();
    }

}
