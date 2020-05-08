package com.beijing.day04;

/*
    hashMap 线程不安全的，如何让HashMap线程安全，，，
    集合工具类也带有方法 Collections.synchronizedMap(new HashMap<>());
    juc 提供了一个方法 new ConcurrentHashMap<>();

    ArrayList 扩容为原来的一半
    HashMap 扩容为远来的一倍,,默认扩容16 加载系数0.75  16 是2的4次方 每次会直属加1,,


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThreadDay0403 {

    public static void main(String[] args) {
        //Map<Object, Object> map = new ConcurrentHashMap<>();
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());


        for (int i = 0; i < 30; i++) {
            final int a = i;
            new Thread(()->{
                map.put(Thread.currentThread().getName(),a);
                System.out.println("a = " + map);
            }).start();
        }
        System.out.println("map = " + map);

    }

}
