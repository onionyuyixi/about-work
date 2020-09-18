package com.example.aboutwork.queue;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component("transferQueueDemo")
public class TransferQueueDemo extends LinkedTransferQueue<Integer> {

    //transferQueue适合单线程生产  多线程消费的情况 可以确保消费者一定会消费的
    private static TransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();

    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    final ExecutorService mutExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @AllArgsConstructor
    static class Producer implements Runnable {
        LocalDateTime dateTime;
        Integer num;

        @Override
        public void run() {
            System.err.println("生产时间===>" + dateTime + "----数据" + num);
            try {
                transferQueue.transfer(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(1000);
            Integer num = transferQueue.poll();
            System.out.println("消费数字===>" + num);
        }
    }

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
            return transferQueue.offer(integer, 10, TimeUnit.MILLISECONDS);
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


    public Object singleProducer() {
        while (true) {
            Producer producer = new Producer(LocalDateTime.now(), new Random().nextInt(10000));
            producer.run();
        }
    }

    public Object mutProducer() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            Producer producer = new Producer(LocalDateTime.now(), atomicInteger.getAndIncrement());
            executorService.execute(producer);
        }
    }

    public void mutConsumer() throws InterruptedException {
        while (true) {
            Thread.sleep(10);
            Consumer consumer = new Consumer();
            mutExecutorService.execute(consumer);
        }


    }
}
