package com.example.knowledge.threadpool;

/**
 * 工作线程
 */
public class AccessDBThread implements Runnable{

    private String msg;

    public AccessDBThread() {

    }

    public AccessDBThread(String msg) {
        this.msg = msg;
    }

    public void run() {
        //向数据库中添加MSG变量值
        System.out.println("Added the message："+msg+" into the Database");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
