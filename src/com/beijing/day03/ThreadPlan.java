package com.beijing.day03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
    多线程开发口诀
    1：高内聚低耦合前提下，线程操作资源类
    2：判断/操作/通知
    3：防止虚假唤醒用while
    4：修改标志位


 */
class Plant {
    private Integer sys = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void plant5() {
        lock.lock();
        try {
            while (sys != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"打印第" + i+"次");
            }
            sys = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void plant10() {
        lock.lock();
        try {
            while (sys != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"打印第" + i+"次");
            }
            sys = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void plant15() {
        lock.lock();
        try {
            while (sys != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"打印第" + i+"次");
            }
            sys = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}

public class ThreadPlan {

    public static void main(String[] args) {
        Plant plant = new Plant();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plant.plant5();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plant.plant10();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plant.plant15();
            }
        },"CC").start();

    }
}
