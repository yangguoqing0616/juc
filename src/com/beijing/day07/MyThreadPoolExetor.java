package com.beijing.day07;

/*
    线程池如何处理最大线程数的
    https://www.cnblogs.com/dennyzhangdd/p/6909771.html?utm_source=itdadao&utm_medium=referral

    cpu密集型
        如果CPU密集型 工作线程数就是cpu可用核数。这样比较保险  最大线程数是处理器个数加1
    IO密集型
        如果存在ID，那么w/c>1，阻塞耗时一般都会比计算耗时的很多倍。
        如果不想做以上的计算，那么可以设置工作线程数为2倍cpu可用线程数。
        IO包括：数据库交互，文件上传下载，网络传输等
 */
import java.util.concurrent.*;

public class MyThreadPoolExetor {

    public static void main(String[] args) {
        // Java 虚拟机中的内存总量
        Runtime.getRuntime().totalMemory();
        //Java 虚拟机预留内存
        Runtime.getRuntime().freeMemory();
        //Java 虚拟机试图使用的最大内存量
        Runtime.getRuntime().maxMemory();

        //获取线程数的方法
        System.out.println("最大线程数"+Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors()+1,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
                //如果超出最大线程数与等待队列的和就会报错
                // new ThreadPoolExecutor.AbortPolicy()
                //如果超出最大线程数与等待队列的和 谁谁通知的给谁
                // new ThreadPoolExecutor.CallerRunsPolicy()
                //如果超出最大线程数与等待队列的和 不报错也不通知
                // new ThreadPoolExecutor.DiscardPolicy()
                //如果超出最大线程数与等待队列的和 移除等待时间最长的任务
                // new ThreadPoolExecutor.DiscardOldestPolicy()
        try {
            for (int i = 0; i < 15; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "->办理业务");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
