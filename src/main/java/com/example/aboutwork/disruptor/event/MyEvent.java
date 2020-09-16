package com.example.aboutwork.disruptor.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyEvent {

    private String uuid;
    private Object body;
    private String header;

    @Override
    public String toString() {
        return "{" +
                "'uuid':" + uuid + '\'' +
                ", 'body':'" + body +
                "', 'header':'" + header + '\'' +
                '}';
    }
}
