package com.example.aboutwork.function;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.aboutwork.function.MyConsumer.strConsumer;

public class TestConcurrent {


    private List<String> sourceData = Lists.newArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private List<String> sourceDataCopy = Lists.newArrayList(sourceData);

    Executor executor = Executors.newFixedThreadPool(10);
    @Test
    public void test1() {
        for (int i = 10; i < 20; i++) {
            int finalI = i;
            executor.execute(() -> sourceData.add(String.valueOf(finalI)));
            executor.execute(() -> sourceData.remove(String.valueOf(finalI)));
        }
        System.err.println(sourceData.equals(sourceDataCopy)); //期望的结果是11-19 的数组
    }

    @Test
    public void test2() {
        for (int i = 10; i < 20; i++) {
            int finalI = i;
            executor.execute(() -> {
                sourceData.add(String.valueOf(finalI));
                sourceData.remove(String.valueOf(finalI));
            });
        }
        System.err.println(sourceData.equals(sourceDataCopy)); //期望的结果是11-19 的数组
    }

    @Test
    public void test3() {
        for (int i = 0; i < 200; i++) {
            int finalI = i;
            executor.execute(() -> strConsumer.accept(sourceData,String.valueOf(finalI)));
        }
        System.err.println(sourceData.equals(sourceDataCopy));
    }


}
