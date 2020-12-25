package com.example.aboutwork.executors;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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
     *
     * @param c
     * @return
     */
    private static int runStateOf(int c) {
        return c & ~CAPACITY;     //按小于0 表示当前的c的高位也是1 是一个负数 结果为~CAPACITY表明状态RUNNING  否则得到的结果都是0
    }

    /**
     * 永远是个非负数
     *
     * @param c
     * @return
     */
    private static int workerCountOf(int c) {
        return c & CAPACITY;  //案大于0 的时候 CAPACITY 二进制全是1  计算结果就是c本身
        // 小于0的时候 则等于 CAPACITY-c
    }

    /**
     * 结果是 rs + wc 之和
     *
     * @param rs ~CAPACITY / 0
     * @param wc 非负数
     * @return
     */
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void testRunStateOf() {
        int a = 100;
        int b = -100;
        Assert.assertEquals(runStateOf(a), 0);
        Assert.assertEquals(runStateOf(b), ~CAPACITY);
    }

    @Test
    public void testWorkerCountOf() {
        int a = 100;
        int b = -100;
        Assert.assertEquals(workerCountOf(a), a);
        Assert.assertEquals(workerCountOf(b) - 1, CAPACITY - 100);
    }

    @Test
    public void testCltOf() {
        int b = 10;
        Assert.assertEquals(ctlOf(0, b), rsPlusWc(0, b));
        int f = 0;
        Assert.assertEquals(ctlOf(~CAPACITY, f), rsPlusWc(~CAPACITY, f));
        int g = 100;
        Assert.assertEquals(ctlOf(~CAPACITY, g), rsPlusWc(~CAPACITY, g));
    }

    private int rsPlusWc(int rs, int wc) {
        return rs + wc;
    }


    private final class Worker
            extends AbstractQueuedSynchronizer
            implements Runnable {
        /**
         * This class will never be serialized, but we provide a
         * serialVersionUID to suppress a javac warning.
         */
        private static final long serialVersionUID = 6138294804551838833L;

        /**
         * Thread this worker is running in.  Null if factory fails.
         */
        final Thread thread;
        /**
         * Initial task to run.  Possibly null.
         */
        Runnable firstTask;
        /**
         * Per-thread task counter
         */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         *
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        /**
         * Delegates main run loop to outer runWorker
         */
        public void run() {
            runWorker(this);
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }


    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((ctl.get() < STOP) ||
                        (Thread.interrupted() &&
                                (ctl.get() > STOP)) &&
                                !wt.isInterrupted())
                    wt.interrupt();
                try {
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x;
                        throw x;
                    } catch (Error x) {
                        thrown = x;
                        throw x;
                    } catch (Throwable x) {
                        thrown = x;
                        throw new Error(x);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        }catch (Exception e){
            System.err.println(e.getStackTrace());
        }
    }


    @Test
    public  void testRunWorker(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            if(finalI/4==0) {
                Worker worker = new Worker(() -> System.err.println("yuyixi" + finalI));
                executorService.execute(worker);
            }else {
                executorService.execute(new Worker(null));
            }
        }

    }
}