package com.beijing.day04;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    创建线程的4大方法
    1.extends Thread 类  重写里面的run方法
    2.implements Runnable 接口

 */
class MyThread01 extends Thread{
    @Override
    public void run() {
        System.out.println("111111");

    }
}

//方法2
class MyThread02 implements Runnable{

    @Override
    public void run() {

    }
}

//方法3
class MyThread03 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(222);
        return 1024;
    }
}



public class ThreadDay0404{

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //方法1
        /*new MyThread01().start();
        new MyThread01().start();*/

        //方法2
        Runnable myThread02 = new MyThread02();
        new Thread(myThread02,"AA");

        //方法3
        //第一步，先new一个类
        MyThread03 myThread03 = new MyThread03();
        //第二步，用FutureTask类包装下，注意：FutureTask类继承了Runnable接口
        FutureTask<Integer> futureTask = new FutureTask<>(myThread03);
        //第三步，启动
        new Thread(futureTask).start();
        //第四步，得到线程返回的结果，注意这里是阻塞式的。必须执行完线程才能拿到结果
        System.out.println(futureTask.get());

    }

}
