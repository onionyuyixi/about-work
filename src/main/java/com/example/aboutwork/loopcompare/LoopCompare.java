package com.example.aboutwork.loopcompare;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LoopCompare {

    static List<Long> list = Lists.newArrayList();

    @Before
    public void init() {

        for (long i = 0; i < 5000 * 5000; i++) {
            list.add(i);
        }
    }

    @After
    public void end() {
        list = new ArrayList<>();
    }

    @Test
    public void testForEach() {
        // 2m23s121 ---26    10 41 974
        Long sum = 0L;
        for (Long integer : list) {
            sum = sum + integer;
        }
        System.err.println(sum);


    }


    @Test //17 997    1 7 730
    public void testForEach1() {
        //13 974     27 279
        Long sum = list.stream().reduce(0L, Long::sum);
        System.err.println(sum);
    }


}
