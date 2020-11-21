package com.example.knowledge.threadpool;

public class TestThreadPool {

    public static void main(String[] args) {
        // 创建3个线程的线程池
        ThreadPool t = ThreadPool.getThreadPool(3);
        t.execute(new ITaskInterface[] { new Task(), new Task(), new Task() });
        t.execute(new ITaskInterface[] { new Task(), new Task(), new Task() });
        System.out.println(t);
        t.destroy();// 所有线程都执行完成才destory
        System.out.println(t);
    }

    // 任务类
    static class Task implements ITaskInterface {
        private static volatile int i = 1;

        public void star()
        {
            System.out.println("由旁边发布的任务 " + (i++) + "已经完成");
        }

    }
}
