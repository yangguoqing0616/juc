package com.beijing.day04;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/*
    并发修改异常: concurrentModificationException
    处理方法

    hashSet 是集合不安全类
    hashSet 的底层是hashMap,set 的add方法 其实调的就是map的hashMap的put方法，，只不过这个只是保存的map的key，，
    value 是Object对象



 */
public class ThreadDay0402 {



    public static void main(String[] args) {

        //集合不安全的类
        //Set<String> set  = new HashSet<>();
        //处理为集合安全的
        //Set<String> set  = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(set);
            }).start();
        }

    }


}
