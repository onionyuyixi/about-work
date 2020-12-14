package com.example.aboutwork.executors;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadExecutor {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits  運行狀態保存在最高位上
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl

    /**
     * 结果只有~CAPACITY 和 0 两种
     * @param c
     * @return
     */
    private static int runStateOf(int c) {
        return c & ~CAPACITY;     //按小于0 表示当前的c的高位也是1 是一个负数 结果为~CAPACITY表明状态RUNNING  否则得到的结果都是0
    }

    /**
     * 永远是个非负数
     * @param c
     * @return
     */
    private static int workerCountOf(int c) {
        return c & CAPACITY;  //案大于0 的时候 CAPACITY 二进制全是1  计算结果就是c本身
        // 小于0的时候 则等于 CAPACITY-c
    }

    /**
     * 结果是 rs + wc 之和
     * @param rs  ~CAPACITY / 0
     * @param wc  非负数
     * @return
     */
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void testRunStateOf(){
        int a = 100;
        int b = -100;
        Assert.assertEquals(runStateOf(a),0);
        Assert.assertEquals(runStateOf(b),~CAPACITY);
    }

    @Test
    public void testWorkerCountOf(){
        int a = 100;
        int b = -100;
        Assert.assertEquals(workerCountOf(a),a);
        Assert.assertEquals(workerCountOf(b)-1,CAPACITY-100);
    }

    @Test
    public void testCltOf(){
        int b = 10;
        Assert.assertEquals(ctlOf(0,b),rsPlusWc(0,b));
        int f = 0;
        Assert.assertEquals(ctlOf(~CAPACITY,f),rsPlusWc(~CAPACITY,f));
        int g = 100;
        Assert.assertEquals(ctlOf(~CAPACITY,g),rsPlusWc(~CAPACITY,g));
    }

    private int rsPlusWc(int rs , int wc){
        return rs+wc;
    }

}