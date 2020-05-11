package com.beijing.day06;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolExecutor {

    public static void main(String[] args) {

        //一池5个工作线程
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //一池一个工作线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池N线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 在工作");
                });
            }
        } catch (Exception e) {

        } finally {
            executorService.shutdown();
        }


    }
}
