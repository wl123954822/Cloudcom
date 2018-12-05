package com.wl.serviceuseradmin.vo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 模拟10个客户,2个商品
        for (int index= 0 ; index <10 ; index++) {
            final int userId = index/9;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    HttpClientOp.doPost("http://localhost:8888//commodity/delItemNum","itemId=1&delNum=1&oldVersion=1");
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }



}
