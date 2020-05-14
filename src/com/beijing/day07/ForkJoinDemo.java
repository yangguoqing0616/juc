package com.beijing.day07;

import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask {
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }

        } else {
            int middle = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork();
            task02.fork();
            Object join = task01.join();

        }
        return result;


    }
}

public class ForkJoinDemo {
    public static void main(String[] args){

    }
}

