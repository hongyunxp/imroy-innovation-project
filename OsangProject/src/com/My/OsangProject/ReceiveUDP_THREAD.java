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
        // ����һ���� DatagramPacket ����
        DatagramPacket packet = new DatagramPacket(data, data.length);

        // ʹ�� receiver �������տͻ��������͵����ݣ� ����ͻ���û�з������ݣ� ��������
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
