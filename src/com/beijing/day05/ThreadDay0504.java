package com.beijing.day05;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    ReadWriteLock
 */
class Thread0504 {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object object) {
        readWriteLock.writeLock().lock();

        try {

            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "--->开始写入数据");
            map.put(key, object);
            System.out.println(Thread.currentThread().getName() + "---->写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "开始读取数据");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           readWriteLock.readLock().unlock();
        }

    }

}

public class ThreadDay0504 {

    public static void main(String[] args) {
        Thread0504 thread0504 = new Thread0504();
         for (int i = 0; i < 5; i++) {
             final int as = i;
            new Thread(()->{
                thread0504.put(String.valueOf(as),"q");
            },"线程"+i).start();
        }

        for (int i = 0; i < 10; i++) {
            final int as = i;
            new Thread(()->{
                thread0504.get(String.valueOf(as));
            },"线程"+i).start();
        }
    }


}
