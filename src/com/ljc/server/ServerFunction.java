package com.ljc.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ServerFunction {

    public static void put(Server server){
        System.out.println("put");
        Socket s = null;
        try{
            s = server.ServerThread.accept();
            //下载文件
            DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));//从客户端接收存放上传文件路径的输出流

            int bufferSize = 8192;
            // 缓冲区
            byte[] buf = new byte[bufferSize];
            int passedlen = 0;

            long len = 0;
            String savePath = "E:/上传";
            // 获取文件名称             保存上传 文件的路径名
            savePath =  savePath+ File.separator+dis.readUTF();
            //在本地路径建一个数据流
            DataOutputStream fileOut = new DataOutputStream(
                    new BufferedOutputStream( new FileOutputStream(savePath)));
            // 获取文件长度
            len = dis.readLong(); //从输入流  中读取8个字节

            System.out.println("文件的长度为:" + len + "  KB");
            System.out.println("开始接收文件!");

            // 获取文件
            while (true) {
                int read = 0;
                if (dis != null) {
                    read = dis.read(buf); //从输入流将数据读到缓冲区中，并将返回结果赋给read
                }
                passedlen += read;
                if (read == -1) {
                    break;
                }
                System.out.println("文件接收了" + (passedlen * 100 / len) + "%");
                fileOut.write(buf, 0, read);
            }
            System.out.println("接收完成，文件存为" + savePath);
            fileOut.close();
            dis.close();
            s.close();
        }catch(IOException e ){

        }

    }
    public static void cd(Server server){
        System.out.println("cd");
        Socket s = null;
        try{
            s = server.ServerThread.accept();
            //读取要到达的改变的目录：
            DataInputStream dis = new DataInputStream(new BufferedInputStream(s
                    .getInputStream()));
            String shareFile = dis.readUTF();

            if (shareFile.equals(".."))
            {
                if (!server.shareFiledirectory.equals("E:\\bili"))
                {
                    int n_lastlength = server.shareFiledirectory.lastIndexOf("\\");
                    server.shareFiledirectory = server.shareFiledirectory.substring(0, n_lastlength);
                }
            }else
            {
                server.shareFiledirectory = server.shareFiledirectory + "\\" + shareFile;
            }

//			   shareFiledirectory =
//					   shareFiledirectory + "\\" + shareFile;
            dir(server);
        }catch(IOException e){

        }finally{

        }

    }
    public static void dir(Server server) {
        System.out.println("当前路径：" + server.shareFiledirectory);
        server.rootDirectory = new File(server.shareFiledirectory);//shareFiledirectory表示共享文件的路径
        server.fileArrayList.clear();
        //String[] myList;//定义一个字符串数组
        //myList = rootDirectory.listFiles();
        //for(int i = 0;i<myList.length;i++){
        //	System.out.println(myList[i]);
        //}
        //catch(Exception e){
        //	e.printStackTrace();
        //}
        //}
			 /* File f=new File(shareFiledirectory);
			File[] list=f.listFiles();
			for(int i=0;i<list.length;i++)
			System.out.println(list[i].getAbsolutePath());//打印全路径*/

        initFileArrayList(server);
        String direcFile = "";
        for(int i =0;i<server.fileArrayList.size();i++){
//				System.out.println(fileArrayList.get(i).getAbsolutePath());
            direcFile = direcFile + server.fileArrayList.get(i).getAbsolutePath() + '\n';
        }
        try{
            Socket s = server.ServerThread.accept();
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(s.getOutputStream()));
//			System.out.println("传之前是什么样的:");
//			System.out.println(direcFile);
            byte []buf = direcFile.getBytes();
            dos.write(buf);
            dos.flush();
            dos.close();
            s.close();
        }catch(IOException e){

        }
    }

    public static void get(Server server){
        System.out.println("get"+1111);

        Socket s = null;//连接为空
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try{
            s = server.ServerThread.accept();
            //接受对方的要求的文件名：
            dis = new DataInputStream(new BufferedInputStream(s
                    .getInputStream()));//
            String filePath = dis.readUTF();//
            System.out.println(filePath);
            //传输文件：
            dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

            File record = new File("D:\\Record.txt");
            FileWriter fw = new FileWriter(record, true);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date()).toString();
            String s_ip = s.getInetAddress().toString();
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            fw.write(date + "\r\n");
            fw.write(filePath + "  to" + s_ip + "\r\n");
            fw.flush();
            fw.close();

            File file = new File(filePath);
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();

            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));

            int BUFSIZE = 8192;
            byte [] buf = new byte[BUFSIZE];

            while(true){
                int read = 0;
                if(dis != null){
                    read = dis.read(buf);
                }else{
                    System.out.println("no file founded!");
                    break;
                }
                if (read == -1){
                    break;
                }
                dos.write(buf, 0, read);
            }
            dos.flush();



        }catch(IOException e){
            System.out.println("asdfsssssssssss");
        }finally{
            try{
                dos.close();
                dis.close();
                s.close();
            }catch(IOException e){

            }
        }

    }

    public static void initFileArrayList(Server server) { // 将目录下所有文件放在一个数组列表里面fileArrayList
        if (server.rootDirectory.isDirectory()) {
            // 遍历目录下面的文件和子目录
            File[] fileList = server.rootDirectory.listFiles();
            System.out.println(fileList.length);
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件,添加到文件列表中
//					if (fileList[i].isFile()) {


                server.fileArrayList.add(new File(fileList[i].getAbsolutePath()));
//						System.out.println(fileList[i].getAbsolutePath());
//						System.out.println("添加了" + fileList[i].getName());
//					}
                // 否则递归遍历子目录
//					else if (fileList[i].isDirectory()) {
//						System.out.println("文件");
//						fileList[i].mkdir();//
//						rootDirectory = fileList[i];
//						initFileArrayList();
//					}

            }
        }

    }

}
