package com.example.aboutwork.callBack;


import lombok.Data;

@Data
@MyCallBack(value = ProcessorInterface.class)
public class UseMyProcessor {

    String before;

    String after;

    String use;

    public void use() {
        System.err.println("use MyCallBack");
    }


}
