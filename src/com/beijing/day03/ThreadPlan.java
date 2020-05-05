package com.beijing.day03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
    多线程开发口诀
    1：高内聚低耦合前提下，线程操作资源类
    2：判断/操作/通知
    3：防止虚假唤醒用while
    4：修改标志位

    线程的6大状态：
    1、new， 新建
    2、runnable，运行
    3、blocked，阻塞
    4、waiting，等待
    5、timed-waiting 定时等待
    6、terminated 结束

    认识一个新让线程睡觉的方法 TimeUnit.SECONDS.sleep(4);

    线程出现的第一个异常 InterruptedException
    https://blog.csdn.net/derekjiang/article/details/4845757
    https://www.jianshu.com/p/a8abe097d4ed

   监视器（锁）
   如果synchronized 用在实力方法1上 监视器就是当前对象即this与此同时类里面的实例方法2还有一个synchronized的话第二个线程访问
   访问实例方法2，是需要等待的，因为实例方法一还没哟释放锁资源，

   如果synchronized用在静态方法一上 此时监视器是class模板类，，如果对同一个模板创建两个对象，虽然是两个对象但是监视器还是同一个
   如果第一个抢占到锁资源的线程没有释放锁，，第二个线程还是需要等待
   synchronized 修饰实例方法 监视器是当前对象this
   synchronized 修饰的是静态方法 监视器是当前类class模板

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

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
