package com.example.aboutwork.disruptor.eventFactory;

import com.example.aboutwork.disruptor.event.MyEvent;
import com.lmax.disruptor.EventFactory;

public class MyEventFactory implements EventFactory<MyEvent> {


    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
