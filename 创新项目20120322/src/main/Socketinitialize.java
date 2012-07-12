package main;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Socketinitialize extends Thread {
 
	//存储有所有连接类的线程
	public static ArrayList<ServerThread> allthread = new ArrayList();
	public static ArrayList<clientmsg> allmsg = new ArrayList();


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			socket_initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void socket_initialize() throws IOException {
		ServerSocket ss = new ServerSocket(30000);// 程序端口********
		while (true) {
			Socket s = ss.accept();
			ServerThread tem_serverthread = new ServerThread(s);
			tem_serverthread.start();
			allthread.add(tem_serverthread);
		}
	}
}
