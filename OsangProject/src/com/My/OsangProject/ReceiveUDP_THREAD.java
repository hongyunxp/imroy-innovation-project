package com.My.OsangProject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.os.AsyncTask;

public class ReceiveUDP_THREAD extends AsyncTask{


	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		try{
		DatagramSocket socket = new DatagramSocket(4567);

        byte data[] = new byte[1024];
        // 创建一个空 DatagramPacket 对象
        DatagramPacket packet = new DatagramPacket(data, data.length);

        // 使用 receiver 方法接收客户端所发送到数据， 如果客户端没有发送数据， 进程阻塞
        socket.receive(packet);
        String result = new String(packet.getData(), packet.getOffset(),
                packet.getLength());

    }
    catch (SocketException e1)
    {
        e1.printStackTrace();
    }
    catch (IOException e1)
    {
        e1.printStackTrace();
    }
		return null;
	}

}
