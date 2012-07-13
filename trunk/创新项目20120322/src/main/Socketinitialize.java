package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Socketinitialize extends Thread {
 
	//存储有所有连接类的线程
	public static ArrayList<ServerThread> allthread = new ArrayList<ServerThread>();
	//每一个连接线程与一个clientMsg类相关联
	public static ArrayList<clientMsg> allmsg = new ArrayList<clientMsg>();


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
		ServerSocket ss = new ServerSocket(30000);
		while (true) {
			Socket s = ss.accept();
			ServerThread tem_serverthread = new ServerThread(s);
			tem_serverthread.start();
			allthread.add(tem_serverthread);
		}
	}
}
