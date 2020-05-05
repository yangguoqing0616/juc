package com.beijing.day02;

/*
    多线程 口诀
    1，高内聚低耦合前提下，，线程操作资源类
    2，判断 ->干活 -> 通知
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class plan03 {

    private Integer number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void add (){

        lock.lock();
        try {
            while (number != 0){
                try {
                    condition.await();//this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"******>>>number = " + number);
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public  void div(){

        lock.lock();
        try {
            while (number == 0){
                try {
                    condition.await();//this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"******>>>number = " + number);
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
}

public class Thread03 {

    public static void main(String[] args) {
        plan03 plan = new plan03();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plan.add();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plan.div();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plan.add();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                plan.div();
            }
        },"D").start();
    }
}
