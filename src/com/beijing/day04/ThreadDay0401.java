package com.beijing.day04;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


/*
    1，ArrayList不是线程安全的集合 出现多线程修改异常（的异常） ：ConcurrentModificationException
    2，解决方案
        高并发多线程情况下用以下几个
        2.1，Vector vector 的add方法加了synchronized
        2.2，Collections.synchronizedList(new ArrayList());
        2.3. new CopyOnWriteArrayList<>();



    写时复制 CopyonWrite容器即写时复制的容器。往一个容 器添加元素的时候，不直接往当前容器object[]添加，
    而是先将当前容器Object[]进行copy, 复制出一个新的容器Object[] newElements, 然后新的容器object[] newElements 里添加元素，
    添加完元素之后， 再将原容器的引用指向新的容器setArray(newElements);. 这样做的好处是可以对CopyonWrite容器进行并发的读，
    而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一“种读写分离的思想， 读和写不同的容器
    public boolean add(E e){
    final ReentrantLock lock = this.lock;
    lock. lock();

    try{
    object[] ele ments = getArray();
    int len = elements. length;
    object[] newElements = Arrays.copyof(elements, len + 1);
    newElements[len] = e;
    setArray(newElements);
    return true;
    }
    finally {
    lock. unlock();

}

}

 */

public class ThreadDay0401 {

    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"AA").start();
        }

    }
}
