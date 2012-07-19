package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPThread extends Thread{

    private String LocalIP=null;
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
        try
        {
            int i =0;
            while(i!=256){
                String s = PartIP + i;
           System.out.println(s);
            // ����һ�� DatagramSocket ���󣬲�Ҫ�ж˿ںţ��������õĶ˿ڱ������ռ��
           DatagramSocket socket = new DatagramSocket();
            
            // ����һ�� InetAddress
            InetAddress serverAddress = InetAddress.getByName(s);
            // ���������ַ���
            String str = "2010�������߼�#����Ա#רҵ����#2��#30��ʱ"+"#"+s;
            // ת�����ֽ�����
            byte data[] = str.getBytes("utf-8");

            // �����Թ� DatagramPacket ���� ָ���䷢�͵�ַ�Ͷ˿ں�
            DatagramPacket packet = new DatagramPacket(data, data.length,
                    serverAddress, 4567);

            // ���� socket ����� send()������������
            socket.send(packet);
            System.out.println(i);
            i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
