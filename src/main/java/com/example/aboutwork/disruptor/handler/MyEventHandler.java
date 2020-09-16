package com.example.aboutwork.disruptor.handler;

import com.example.aboutwork.disruptor.event.MyEvent;
import com.lmax.disruptor.EventHandler;

public class MyEventHandler  implements EventHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.err.println(event);
    }
}
