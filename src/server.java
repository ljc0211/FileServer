import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
public class server {
    int n_lastlength = 0;
    ServerSocket ss = null;
    String dir = "";//显示目录
    String cmd = "";
    DataOutputStream dos;
    String direcFile = "";
    File rootDirectory;
    String shareFile;
    DataInputStream dis ;
    //ArrayList <File> fileArrayList = new ArrayList <File>();
    ArrayList<File> fileArrayList = new ArrayList<File>();
    private String shareFiledirectory;
    public static void main(String[] args) {
        //System.out.println("等待连接");
        new server().serverThread();


    }
    public server(){
        this.shareFiledirectory = "E:\\bili";
    }
    public void serverThread()
    {
        Thread startThread = new Thread(new Runnable() { // 多线程
            public void run() {
                serverRun(); // 将服务端端口号设置为默认值9999
            }
        });
        startThread.start();
    }
    public void serverRun()
    {
        //建立套接字
        try {
            ss  = new ServerSocket(8888);//建立提供服务器的端口号为8888
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true)
        {

            try{

                while(true){

                    boolean flag = true;

                    while(flag){
                        Socket s = ss.accept();//阻塞式，等待客户端请求连接
                        System.out.println("已有客户端连接");
                        //接受对方的要求：
                        dis = new DataInputStream(new BufferedInputStream(s
                                .getInputStream()));//BufferedInputStream 利用缓冲区来提高读效率，
                        //             从此套接字读取字节的输入流

                        //BufferedInputStream(InputStream in) 参数in指定需要被装饰的输入流
                        cmd= dis.readUTF();//从dis输入流读取若干字节，把它转换为采用UTF-8字符编码的字符串,并将其放在cmd String变量里
                        //UTF-8对ASCII字符采用一个字节形式的编码，对非ASCII字符则采用两个或两个以上字节形式的编码
                        dis.close();//关闭输入流
                        s.close();//关闭socket

                        System.out.println("输出读入的字符串："+cmd);
                        //   System.out.println(cmd=="get");

                        //接受后放在cmd里用于判断：
                        if(cmd.equals("get"))
                            get();
                        else if(cmd.equals("put"))
                            put();
                        else if(cmd.equals("cd"))
                            cd ();
                        else if(cmd.equals("dir"))
                            dir();
                        else if(cmd == "quit")
                            flag = false;


                    }


                }
            }catch (IOException e){

            }
        }
    }

    public void get(){
        System.out.println("get"+1111);

        Socket s = null;//连接为空
        try{
            s = ss.accept();
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
    public void put(){
        System.out.println("put");
        Socket s = null;
        try{
            s = ss.accept();
            //下载文件
            dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));//从客户端接收存放上传文件路径的输出流

            int bufferSize = 8192;
            // 缓冲区
            byte[] buf = new byte[bufferSize];
            int passedlen = 0;

            long len = 0;
            String savePath = "E:/上传";
            // 获取文件名称             保存上传 文件的路径名
            savePath =  savePath+File.separator+dis.readUTF();
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
    public void cd(){
        System.out.println("cd");
        Socket s = null;
        try{
            s = ss.accept();
            //读取要到达的改变的目录：
            dis = new DataInputStream(new BufferedInputStream(s
                    .getInputStream()));
            shareFile = dis.readUTF();

            if (shareFile.equals(".."))
            {
                if (!shareFiledirectory.equals("E:\\bili"))
                {
                    n_lastlength = shareFiledirectory.lastIndexOf("\\");
                    shareFiledirectory =
                            shareFiledirectory.substring(0, n_lastlength);

                }
            }else
            {
                shareFiledirectory =
                        shareFiledirectory + "\\" + shareFile;
            }

//			   shareFiledirectory =
//					   shareFiledirectory + "\\" + shareFile;
            dir();
        }catch(IOException e){

        }finally{

        }

    }
    public void dir ( ) throws IOException{
        System.out.println("当前路径：" + shareFiledirectory);
        rootDirectory = new File(shareFiledirectory);//shareFiledirectory表示共享文件的路径
        fileArrayList.clear();
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

        initFileArrayList();
        direcFile = "";
        for(int i =0;i<fileArrayList.size();i++){
//				System.out.println(fileArrayList.get(i).getAbsolutePath());
            direcFile = direcFile+fileArrayList.get(i).getAbsolutePath()+'\n';
        }
        try{
            Socket s = ss.accept();
            dos = new DataOutputStream(
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

    public void initFileArrayList() { // 将目录下所有文件放在一个数组列表里面fileArrayList
        if (rootDirectory.isDirectory()) {
            // 遍历目录下面的文件和子目录
            File[] fileList = rootDirectory.listFiles();
            System.out.println(fileList.length);
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件,添加到文件列表中
//					if (fileList[i].isFile()) {


                fileArrayList.add(new File(fileList[i].getAbsolutePath()));
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