package com.beijing.day02;

/*
    多线程 口诀
    1，高内聚低耦合前提下，，线程操作资源类
    2，判断 ->干活 -> 通知
 */

/*
    题目：
    现有两个线程操作一个资源类，，，初始化变量为0；
    实现一个线程对该变量加1，一个线程对该变量减1
    实现交替来10轮
 */

/**
  * 
  * @Description:
  *现在两个线程，
  * 可以操作初始值为零的一个变量，
  * 实现一个线程对该变量加1，一个线程对该变量减1，
  * 交替，来10轮。 
  * @author xialei
  *
  *  * 笔记：Java里面如何进行工程级别的多线程编写
  * 1 多线程变成模板（套路）-----上
  *     1.1  线程    操作    资源类  
  *     1.2  高内聚  低耦合
  * 2 多线程变成模板（套路）-----下
  *     2.1  判断
  *     2.2  干活
  *     2.3  通知
  
  */
class plan {

    private Integer number = 0;

    public synchronized void add (){

        if (number != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"******>>>number = " + number);
        this.notifyAll();
    }

    public synchronized void div(){
        if(number != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"---->number = " + number);
        this.notifyAll();
    }
}

public class Thread01 {

    public static void main(String[] args) {
        plan plan = new plan();
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

    }
}
