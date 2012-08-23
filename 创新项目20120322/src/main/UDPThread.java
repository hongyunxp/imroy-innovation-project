package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPThread extends Thread{

    private String LocalIP=null;
    // ����һ�� DatagramSocket ���󣬲�Ҫ�ж˿ںţ��������õĶ˿ڱ������ռ��

    /**
     * 
     * @param ���캯��������Ǳ���IP��ַ
     */
    UDPThread(String s){
        this.LocalIP=s;
    }
    @Override
    public void run() {
        
        String PartIP = LocalIP.substring(0, LocalIP.lastIndexOf(".")+1);
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while(true)
        {
        try
        {
            int i =0;
            while(i!=256){
                String s = PartIP + i;
           //System.out.println("���ڷ���UDP�㲥��:"+s);

            
            // ����һ�� InetAddress
            InetAddress serverAddress = InetAddress.getByName(s);
            // ���������ַ���
            String str = "2010�������߼�#����Ա#רҵ����#2��#30��ʱ"+"#"+LocalIP;
            // ת�����ֽ�����
            byte data[] = str.getBytes("utf-8");

            // �����Թ� DatagramPacket ���� ָ���䷢�͵�ַ�Ͷ˿ں�
            DatagramPacket packet = new DatagramPacket(data, data.length,
                    serverAddress, 4567);

            // ���� socket ����� send()������������
            socket.send(packet);
            //System.out.println(i);
            i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        }
    }

}
