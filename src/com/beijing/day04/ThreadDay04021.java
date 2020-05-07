package com.beijing.day04;

/*

    hashSet 是集合不安全类
    hashSet 的底层是hashMap,set 的add方法 其实调的就是map的hashMap的put方法，，只不过这个只是保存的map的key，，
    value 是Object对象
 */

import java.util.HashSet;

public class ThreadDay04021 {

    public static void main(String[] args) {
        HashSet<Object> objects = new HashSet<>();
        objects.add(1);
    }


}
