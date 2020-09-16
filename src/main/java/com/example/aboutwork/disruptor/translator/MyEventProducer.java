package com.example.aboutwork.disruptor.translator;

import com.example.aboutwork.disruptor.event.MyEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class MyEventProducer {

    private final RingBuffer<MyEvent> ringBuffer;

    public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        long next = ringBuffer.next();
        try {
            MyEvent myEvent = ringBuffer.get(next);
            myEvent.setBody(byteBuffer.toString());
        }finally {
            ringBuffer.publish(next);
        }
    }



}
