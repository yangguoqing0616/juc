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


    改成4个线程时if判断就会有问题，，

    解释为什么用if就会有问题：
    现在4个线程跑两个加，两个减，，假如（加）其中线程正好走进if但是还没有掉wait方法时，，cpu的时间切片突然换成了另一个加法，
    通过判断之后还会进去并且完成了加1操作，，之后停的线程突然又开始跑，，，这时候就会有问题
    出现这样的问题就是没有重现判断
    用while可以解决这样的问题，，，，也是官方推荐的做法

    调用wait方法后线程会释放锁进入等待池中，等待再次调用，，如果调用到就会在暂停的地方继续往下执行

    1、wait()方法属于Object类,sleep()属于Thread类；
    2、wait()方法释放cpu给其他线程，自己让出资源进入等待池等待；sleep占用cpu，不让出资源；
    3、sleep()必须指定时间，wait()可以指定时间也可以不指定；sleep()时间到，线程处于临时阻塞或运行状态；
    4、wait()方法会释放持有的锁，不然其他线程不能进入同步方法或同步块，从而不能调用notify(),notifyAll()方法来唤醒线程，产生死锁，所以释放锁，可以执行其他线程，也可以唤醒自己，只是设置停止自己的时间时不确定的；sleep方法不会释放持有的锁，设置sleep的时间是确定的会按时执行的；
    5、wait()方法只能在同步方法或同步代码块中调用，否则会报illegalMonitorStateException异常，如果没有设定时间，使用notify()来唤醒；而sleep()能在任何地方调用；


 */


/*
 * * 
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知
 * 3 防止虚假唤醒用while
 * 
 * 
 * */
class plan02 {

    private Integer number = 0;

    public synchronized void add (){

        while (number != 0){
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
        while(number != 1){
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

public class Thread02 {

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
