package com.beijing.day01;

/*
表达式实现

题目 3个售票员  卖 30张票

 多线程编程的套路

 1 在高内聚低耦合的前提先，，线程       操作      资源类
    1.1 高内聚 是指 资源类里面包含了所有方法
    1.2 操作就是 资源类里面提供的方法
    1.3 资源类 指的是 ticket
 2 线程的状态
      Thread.State
    1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
    2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
        线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，
        获取CPU的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
    3. 阻塞(BLOCKED)：表示线程阻塞于锁。
    4. 等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
    5. 超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
    6. 终止(TERMINATED)：表示该线程已经执行完毕。
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ticket01 {
    private  int number = 3000;
    Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖第"+number--+"张票，剩余"+number);
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }



}

public class SimpleTicket02 {



    public static void main(String[] args) {
        ticket01 ticket = new ticket01();

        new Thread(()->{ for (int i = 0; i < 4000; i++) ticket.saleTicket();},"A").start();
        new Thread(()->{ for (int i = 0; i < 4000; i++) ticket.saleTicket();},"B").start();
        new Thread(()->{ for (int i = 0; i < 4000; i++) ticket.saleTicket();},"C").start();

    }

}
