package com.example.aboutwork.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class ParallelStreamDemo {

        final static List<Integer> numbers = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29
        );
    static void parallelTest() {
        final long begin = System.currentTimeMillis();
        doAction(begin);

    }

    private static void doAction(long begin) {
        numbers.parallelStream().map(k -> {
            try {
                Thread.sleep(1000);
                System.out.println((System.currentTimeMillis() - begin) + "ms => " + k + " \t" + Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return k;
        }).collect(Collectors.toList());
    }

    private static void testWithPool() {
        ForkJoinPool pool = new ForkJoinPool(30);

        final long begin = System.currentTimeMillis();
        try {
            pool.submit(() -> doAction(begin)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();
//        new Thread(() -> parallelTest()).start();

//        testWithPool();
    }

}
