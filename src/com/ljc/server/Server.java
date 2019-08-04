package com.ljc.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private Socket socket;

    private String dir = "";//显示目录
    private DataOutputStream dos;
    private String shareFile;

    public ServerSocket ServerThread;
    public String shareFiledirectory;
    public File rootDirectory;
    public ArrayList<File> fileArrayList = new ArrayList<>();

    public Server() {
        socket = null;
        shareFiledirectory = "E:\\bili";
        ServerThread = null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
