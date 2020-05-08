package com.beijing.day04;

import java.util.*;

/*
    并发修改异常: concurrentModificationException
    处理方法



 */
public class ThreadDay0402 {



    public static void main(String[] args) {

        //Set<String> set  = new HashSet<>();
        Set<String> set  = new LinkedHashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(set);
            }).start();
        }

    }


}
