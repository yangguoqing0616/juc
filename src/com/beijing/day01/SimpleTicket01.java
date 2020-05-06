package com.beijing.day01;

/*
题目 3个售票员  卖 30张票

 多线程编程的套路

 1 在高内聚低耦合的前提先，，线程       操作      资源类
    1.1 高内聚 是指 资源类里面包含了所有方法
    1.2 操作就是 资源类里面提供的方法
    1.3 资源类 指的是 ticket


 */

class ticket {
    private int number = 30;

    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "张票，还剩" + number);
        }
    }

}

public class SimpleTicket01 {


    public static void main(String[] args) {
        ticket ticket = new ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "C").start();

    }
}
