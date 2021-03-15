package com.example.aboutwork.synckeyword;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@Data
public class BaeldungSynchronizedMethods {

    private int sum = 0;
    private static int staticSum = 0;
    private final Object monitor = new Object();

    public void calculate() {
        setSum(getSum() + 1);
    }

    @Test
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        BaeldungSynchronizedMethods summation = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(summation::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, summation.getSum());
    }


    //只能作用在当前class的实例上 与synchronized(monitor)表达的意思一样
    public synchronized void synchronisedCalculate() {
        setSum(getSum() + 1);
    }

    @SneakyThrows
    @Test
    public void givenMultiThread_whenMethodSync() {
        ExecutorService service = Executors.newFixedThreadPool(3);


        BaeldungSynchronizedMethods method = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(method::synchronisedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getSum());
    }


    public static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }


    @SneakyThrows
    @Test
    public void givenMultiThread_whenStaticSyncMethod() {
        staticSum = 0;
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(BaeldungSynchronizedMethods::syncStaticCalculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, BaeldungSynchronizedMethods.staticSum);
    }


    public void performSynchronisedTask() {
        synchronized (monitor) {
            setSum(getSum() + 1);
        }
    }


    @SneakyThrows
    @Test
    public void givenMultiThread_whenBlockSync() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        BaeldungSynchronizedMethods synchronizedBlocks = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(synchronizedBlocks::performSynchronisedTask));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, synchronizedBlocks.getSum());
    }


    public static void performStaticSyncTask() {
        synchronized (Object.class) { //static synchronized 对应的是class  level
            staticSum = staticSum + 1;
        }
    }

    @SneakyThrows
    @Test
    public void givenMultiThread_whenStaticSyncBlock() {
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(BaeldungSynchronizedMethods::performStaticSyncTask));
        service.awaitTermination(50, TimeUnit.MILLISECONDS);

        assertEquals(1000, staticSum);
        synchronized (monitor) {
            staticSum = 0;
        }
    }
}
