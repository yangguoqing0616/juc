package com.beijing.day01;

/*
java8 表达式实现

口诀

    1，拷贝右括号 写死右箭头   落地大括号
    2, @FunctionalInterface 有该注解的接口一定是函数式接口
    3， 接口可以有方法的实现，用default 关键字 但是只能有一个
    4, 静态方法 静态方法可以有多个

 */
@FunctionalInterface
interface Foo{
    public int add(int x,int y);

    default int add2(int x, int y){
        System.out.println("add2加法运算"+ x+y);
        return x+y;
    }

    static int add3(int x,int y){
        return x+y;
    }
    static int add4(int x,int y){
        return x+y;
    }


}

public class SimpleTicket03 {



    public static void main(String[] args) {
        Foo foo = (x,y) ->{
            System.out.println("拷贝大括号，写死右箭头，落地大括号");
            return x+y;
        };
        System.out.println(foo.add(3,4));

        System.out.println(foo.add2(2,3));

        int i = Foo.add3(5, 5);
        System.out.println("i = " + i);
    }

}
