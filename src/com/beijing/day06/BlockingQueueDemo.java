package com.beijing.day06;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/*
抛出异常
    当阻塞队列满时，再往队列里add插入元素会抛llgalStateException:Queuefull
    当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
特殊值
    插入方法，成功ture失败false移除方法，成功返回出队列的元素，队列里没有就返回null
一直阻塞
    当阻塞队列满时，生产者线程继续往队列里put元素,队列会-直阻塞生产者线程直到put数据or响应
    中断退出当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
超时退出
    当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 */




/**
 *  * 阻塞队列
 *  
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

//        List list = new ArrayList();

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //第一组 抛出异常
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.element());

        //System.out.println(blockingQueue.add("x"));
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//    第二组 特殊值
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("x"));
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//    第三组  一直阻塞       
//         blockingQueue.put("a");
//         blockingQueue.put("b");
//         blockingQueue.put("c");
//         //blockingQueue.put("x");
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());

        //    第四组     超时退出    
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));

    }
}

