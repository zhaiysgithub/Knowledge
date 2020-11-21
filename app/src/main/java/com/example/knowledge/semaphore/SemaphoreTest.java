package com.example.knowledge.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    //表示消息池可用消息只有5个
    private static final int MAX_POOL_SIZE = 3;

    //获取消息的客户端的线程数量
    private static final int CLIENT_SIZE = 6;

    //消息数组，存放所有消息
    private static Message[] messages = new Message[MAX_POOL_SIZE];

    //信号量，许可数量为消息的最大可用数量
    private static Semaphore semaphore = new Semaphore(MAX_POOL_SIZE);

    //初始化消息数组
    static void  init() {
        for(int i = 0; i < MAX_POOL_SIZE; i++) {
            messages[i] = new Message();
        }
    }

    //同步方法，获取可用的消息
    static synchronized Message obtain() {
        Message msg = null;
        for(int i = 0; i < MAX_POOL_SIZE; i++) {
            if(messages[i].getFlag() == false) {
                msg = messages[i];
                msg.setId(i);
                msg.setFlag(true);
                return msg;
            }
        }
        return msg;
    }

    //同步方法，把用完的消息放回消息池
    static synchronized boolean release(Message msg) {
        if(msg.getFlag() == true) {
            msg.setFlag(false);
            msg.setId(-1);
            return true;
        }
        return false;
    }

    //用信号量控制能获取消息的数目
    static Message obtainMsg() throws InterruptedException {
        semaphore.acquire();
        return obtain();
    }

    //成功释放消息的同时释放信号量
    static void releaseMsg(Message msg) {
        System.out.print(Thread.currentThread().getName() + " ***Release msg id*** = "+ msg.getId() + "\n");
        if(release(msg)) {
            semaphore.release();
        }
    }

    public static void main(String[] args) {

        //初始化
        init();

        //创建子线程，获取消息
        for (int i = 0; i < CLIENT_SIZE; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取消息
                        Message msg = obtainMsg();
                        System.out.print(Thread.currentThread().getName() + " Obtain msg id = " + msg.getId() + "\n");
                        //假装耗时操作
                        Thread.sleep(1000);
                        //释放消息
                        releaseMsg(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    //声明一个消息类
    static class Message{

        private int id;         //表示每个消息的id
        private boolean flag;   //表示消息是否可用

        public Message() {
            this.id = -1;
            this.flag = false;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFlag(boolean b) {
            this.flag = b;
        }

        public int getId() {
            return this.id;
        }

        public boolean getFlag() {
            return this.flag;
        }
    }
}
