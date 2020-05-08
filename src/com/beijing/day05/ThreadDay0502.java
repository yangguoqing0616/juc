package com.beijing.day05;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadDay0502 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("出发-------------------------");
        });

        for (int i = 0; i < 10; i++) {
            final int a = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"第"+a+"位同学报到");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
