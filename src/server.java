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
    String dir = "";//��ʾĿ¼
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
		//System.out.println("�ȴ�����");
		new server().serverThread();
		

	}
	public server(){
		this.shareFiledirectory = "E:\\bili";
	}
	public void serverThread()
	{
		Thread startThread = new Thread(new Runnable() { // ���߳�
			public void run() {
				serverRun(); // ������˶˿ں�����ΪĬ��ֵ9999
			}
		});
		startThread.start();
	}
	public void serverRun()
	{
		//�����׽���
		try {
			ss  = new ServerSocket(8888);//�����ṩ�������Ķ˿ں�Ϊ8888
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true)
		{
			
			try{
			
			while(true){
				
				boolean flag = true;
				
				while(flag){
					Socket s = ss.accept();//����ʽ���ȴ��ͻ�����������
					System.out.println("���пͻ�������");
				//���ܶԷ���Ҫ��
					 dis = new DataInputStream(new BufferedInputStream(s
							.getInputStream()));//BufferedInputStream ���û���������߶�Ч�ʣ�
			//             �Ӵ��׽��ֶ�ȡ�ֽڵ�������
					 
					 //BufferedInputStream(InputStream in) ����inָ����Ҫ��װ�ε�������
					   cmd= dis.readUTF();//��dis��������ȡ�����ֽڣ�����ת��Ϊ����UTF-8�ַ�������ַ���,���������cmd String������
					   //UTF-8��ASCII�ַ�����һ���ֽ���ʽ�ı��룬�Է�ASCII�ַ���������������������ֽ���ʽ�ı���
					   dis.close();//�ر�������
					   s.close();//�ر�socket
					   
					   System.out.println("���������ַ�����"+cmd);
					//   System.out.println(cmd=="get");
				
				//���ܺ����cmd�������жϣ�
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
			
			Socket s = null;//����Ϊ��
			try{
				s = ss.accept();
				//���ܶԷ���Ҫ����ļ�����
			dis = new DataInputStream(new BufferedInputStream(s
					.getInputStream()));//
			   String filePath = dis.readUTF();//
			   System.out.println(filePath);
			   //�����ļ���
			   
			   dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

			   File record = new File("D:\\Record.txt");
			   FileWriter fw = new FileWriter(record, true);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				String date = df.format(new Date()).toString();
				String s_ip = s.getInetAddress().toString();
				System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
				//�����ļ�
		        dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));//�ӿͻ��˽��մ���ϴ��ļ�·���������
		    
		    	int bufferSize = 8192;   
		        // ������   
		        byte[] buf = new byte[bufferSize];   
		        int passedlen = 0;   
		        
		        long len = 0;   
		        String savePath = "E:/�ϴ�";
		        // ��ȡ�ļ�����             �����ϴ� �ļ���·����
		        savePath =  savePath+File.separator+dis.readUTF();   
		        //�ڱ���·����һ��������
		        DataOutputStream fileOut = new DataOutputStream(   
		                new BufferedOutputStream( new FileOutputStream(savePath)));   
		        // ��ȡ�ļ�����   
		        len = dis.readLong(); //��������  �ж�ȡ8���ֽ�

		        System.out.println("�ļ��ĳ���Ϊ:" + len + "  KB");   
		        System.out.println("��ʼ�����ļ�!");   

		        // ��ȡ�ļ�   
		        while (true) {   
		            int read = 0;   
		            if (dis != null) {   
		                read = dis.read(buf); //�������������ݶ����������У��������ؽ������read  
		            }   
		            passedlen += read;   
		            if (read == -1) {   
		                break;   
		            }   
		            System.out.println("�ļ�������" + (passedlen * 100 / len) + "%");   
		            fileOut.write(buf, 0, read);   
		        }   
		        System.out.println("������ɣ��ļ���Ϊ" + savePath);   
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
				//��ȡҪ����ĸı��Ŀ¼��
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
			System.out.println("��ǰ·����" + shareFiledirectory);
			rootDirectory = new File(shareFiledirectory);//shareFiledirectory��ʾ�����ļ���·��
			fileArrayList.clear();
			//String[] myList;//����һ���ַ�������
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
			System.out.println(list[i].getAbsolutePath());//��ӡȫ·��*/
			
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
//			System.out.println("��֮ǰ��ʲô����:");
//			System.out.println(direcFile);
			byte []buf = direcFile.getBytes();
			dos.write(buf);
			dos.flush();
			dos.close();
			s.close();
			}catch(IOException e){
				
			}
		}
		
		public void initFileArrayList() { // ��Ŀ¼�������ļ�����һ�������б�����fileArrayList
			if (rootDirectory.isDirectory()) {
				// ����Ŀ¼������ļ�����Ŀ¼
				File[] fileList = rootDirectory.listFiles();
				System.out.println(fileList.length);
				for (int i = 0; i < fileList.length; i++) {
					// ������ļ�,��ӵ��ļ��б���
//					if (fileList[i].isFile()) {
						
						
						fileArrayList.add(new File(fileList[i].getAbsolutePath()));
//						System.out.println(fileList[i].getAbsolutePath());
//						System.out.println("�����" + fileList[i].getName());
//					}
					// ����ݹ������Ŀ¼
//					else if (fileList[i].isDirectory()) {
//						System.out.println("�ļ�");
//						fileList[i].mkdir();//
//						rootDirectory = fileList[i];
//						initFileArrayList();
//					}

				}
			}

		}
}
