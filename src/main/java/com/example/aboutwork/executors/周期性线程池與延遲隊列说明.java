package com.example.aboutwork.executors;

public class 周期性线程池與延遲隊列说明 {

    //DelayedWorkQueue  自定义的特殊延迟queue
    //except that  every ScheduledFutureTask also records its index into the  heap array (task中会记录他们在heap中的index)
    //延遲隊列可以實現定時任務

//    核心代碼部分 如何實現的延遲
//    public E take() throws InterruptedException {
//        final ReentrantLock lock = this.lock;
//        lock.lockInterruptibly();
//        try {
//            for (;;) {
//                E first = q.peek();
//                if (first == null)
//                    available.await();
//                else {
//                    long delay = first.getDelay(NANOSECONDS);
//                    if (delay <= 0)
//                        return q.poll();
//                    first = null; // don't retain ref while waiting
//                    if (leader != null)  //leader不爲null 代表當前綫程的前一個當前綫程 還存在 人需要等待
//                      這又被稱作  This variant of the Leader-Follower pattern  （領導-隨從模式之變種）
//                        available.await();
//                    else {
//                       //leader 始終待變當前綫程   默認是null  (private Thread leader = null;)
//                        Thread thisThread = Thread.currentThread();
//                        leader = thisThread;
//                        try {
//                            delay是first的延遲時間
//                            available.awaitNanos(delay);
//                        } finally {
//                            無論順利等待成功 還是中途意外 都得下臺 命同如今之政客 此即位置(leader)未變而就任者不可窮計
//                            if (leader == thisThread)
//                                leader = null;
//                        }
//                    }
//                }
//            }
//        } finally {
//            if (leader == null && q.peek() != null)
//                available.signal();
//            lock.unlock();
//        }
//    }

}
