package com.example.aboutwork.listener;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class 監聽簡易説明 implements Runnable {

    private boolean flag = true;

    @PreDestroy //before close context  exit loop
    public void exitLoop (){
        flag = false;
    }

    //所謂監聽 無非就是spin 一個單獨綫程 只幹一件事 頗有匠人風範
    @Override
    public void run() {
        while (flag) {
            try {

            //
            }catch (Exception e){
                //遇到error的時候 退出循環
                flag = false;
            }
        }
    }
}
