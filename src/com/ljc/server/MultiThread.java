package com.ljc.server;

import com.ljc.server.Server;

public class MultiThread {
    public static void main(String[] args) {
        //System.out.println("等待连接");
        new MultiThread().serverThread();


    }
    public void serverThread()
    {
        Thread startThread = new Thread(new Runnable() { // 多线程
            public void run() {
                MonitorConnection.listening(new Server()); // 将服务端端口号设置为默认值9999
            }
        });
        startThread.start();
    }
}
