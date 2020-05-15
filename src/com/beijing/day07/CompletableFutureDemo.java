package com.beijing.day07;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "/ 没有返回值");
        });
        voidCompletableFuture.get();

        System.out.println("---------------");

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            int i = 10 / 0;
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        completableFuture2.whenComplete((t, u) -> {
            //supplyAsyncy运行正常得到的值就是t
            System.out.println("-------t=" + t);
            //如果错误u就是异常
            System.out.println("-------u=" + u);
        }).exceptionally((f) -> {
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get();

        //打印444
        System.out.println(completableFuture2.whenComplete((t, u) -> {
            //supplyAsyncy运行正常得到的值就是t
            System.out.println("-------t=" + t);
            //如果错误u就是异常
            System.out.println("-------u=" + u);
        }).exceptionally((f) -> {
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get());

    }
}
