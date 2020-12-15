package com.example.aboutwork.distributelock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class ZkDistributeLock {

    private final InterProcessMutex lock;
    private final Runnable runnable;
    private final String clientName;

    public ZkDistributeLock(CuratorFramework client, String lockPath, Runnable runnable, String clientName) {
        this.runnable = runnable;
        this.clientName = clientName;
        lock = new InterProcessMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the lock");
        }
        try {
            System.out.println(clientName + " has the lock");
            runnable.run();
        } finally {
            System.out.println(clientName + " releasing the lock");
            lock.release(); // always release the lock in a finally block
        }
    }




}
