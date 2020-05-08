package com.beijing.day05;

/*
    CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
    其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
    当计数器的值变为0时，因await方 法阻塞的线程会被唤醒，继续执行。|

 */
import java.util.concurrent.CountDownLatch;

public class ThreadDay05 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println("毕业了");
                countDownLatch.countDown();
            },"AA").start();
        }
        countDownLatch.await();
        System.out.println("恭喜------");

    }
}
