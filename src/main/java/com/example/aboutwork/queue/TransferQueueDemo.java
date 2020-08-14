package com.example.aboutwork.queue;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

@Component("transferQueueDemo")
public class TransferQueueDemo extends LinkedTransferQueue<Integer> {

    private TransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();


    @Override
    public void put(Integer integer) {
        try {
            transferQueue.put(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean offer(Integer integer, long timeout, TimeUnit unit) {
        try {
            return transferQueue.offer(integer,1000,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean offer(Integer integer) {
        return transferQueue.offer(integer);
    }

    @Override
    public boolean add(Integer integer) {
        return transferQueue.add(integer);
    }

    @Override
    public boolean tryTransfer(Integer integer) {
        return transferQueue.tryTransfer(integer);
    }

    @Override
    public void transfer(Integer integer) throws InterruptedException {
        transferQueue.transfer(integer);
    }

    @Override
    public boolean tryTransfer(Integer integer, long timeout, TimeUnit unit) throws InterruptedException {
        return transferQueue.tryTransfer(integer, timeout, unit);
    }

    @Override
    public Integer take() throws InterruptedException {
        return transferQueue.take();
    }

    @Override
    public Integer poll(long timeout, TimeUnit unit) throws InterruptedException {
        return transferQueue.poll(timeout, unit);
    }

    @Override
    public Integer poll() {
        return transferQueue.poll();
    }
}
