package com.ljc.server;

import com.ljc.server.Server;
import com.ljc.server.ServerFunction;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class MonitorConnection {

    public static void listening(Server server) {
        //建立套接字
        try {
            server.ServerThread = new ServerSocket(8888);//建立提供服务器的端口号为8888
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true)
        {
            try{
                while(true){
                    boolean flag = true;

                    while(flag){
                        server.setSocket(server.ServerThread.accept());//阻塞式，等待客户端请求连接
                        System.out.println("已有客户端连接");
                        //接受对方的要求：

                        DataInputStream dis = new DataInputStream(new BufferedInputStream(server.getSocket()
                                .getInputStream()));//BufferedInputStream 利用缓冲区来提高读效率，
                        //             从此套接字读取字节的输入流

                        //BufferedInputStream(InputStream in) 参数in指定需要被装饰的输入流
                        String cmd = dis.readUTF();//从dis输入流读取若干字节，把它转换为采用UTF-8字符编码的字符串,并将其放在cmd String变量里
                        //UTF-8对ASCII字符采用一个字节形式的编码，对非ASCII字符则采用两个或两个以上字节形式的编码
                        dis.close();//关闭输入流
                        server.getSocket().close();//关闭socket

                        System.out.println("输出读入的字符串：" + cmd);
                        //   System.out.println(cmd=="get");

                        //接受后放在cmd里用于判断：
                        if(cmd.equals("get"))
                            ServerFunction.get(server);
                        else if(cmd.equals("put"))
                            ServerFunction.put(server);
                        else if(cmd.equals("cd"))
                            ServerFunction.cd(server);
                        else if(cmd.equals("dir"))
                            ServerFunction.dir(server);
                        else if(cmd == "quit")
                            flag = false;


                    }


                }
            }catch (IOException e){

            }
        }
    }


}
