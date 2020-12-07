package com.example.aboutwork.executors;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TestThreadExecutor {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void test1() {
        System.err.println(Integer.toBinaryString(ctl.get()));
        System.err.println((ctl.get()));
        System.err.println(Integer.toBinaryString(CAPACITY));
        System.err.println(Integer.toBinaryString(CAPACITY).length());
        System.err.println(Integer.toBinaryString(~CAPACITY));
        System.err.println((CAPACITY));
        System.err.println(Integer.toBinaryString(CAPACITY+1));
        System.err.println(CAPACITY+1);
        System.err.println(Integer.toBinaryString(RUNNING));
        System.err.println(RUNNING);
        System.err.println(Integer.toBinaryString(SHUTDOWN));
        System.err.println(SHUTDOWN);
        System.err.println(Integer.toBinaryString(STOP));
        System.err.println(STOP);
        System.err.println(Integer.toBinaryString(TIDYING));
        System.err.println(TIDYING);
        System.err.println(Integer.toBinaryString(TERMINATED));
        System.err.println(TERMINATED);
        System.err.println(Integer.toBinaryString(runStateOf(1)));
        System.err.println(Integer.toBinaryString(runStateOf(~CAPACITY)));

        System.err.println(Integer.toBinaryString(runStateOf(1)|workerCountOf(1)));
        System.err.println(Integer.toBinaryString(runStateOf(1)|workerCountOf(1)));
        System.err.println(Integer.toBinaryString((1&~CAPACITY) | (1&CAPACITY)));
        System.err.println(Integer.toBinaryString((2&~CAPACITY) | (2&CAPACITY)));
        System.err.println(Integer.toBinaryString((10&~CAPACITY) | (10&CAPACITY)));
    }

}
