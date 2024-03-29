package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPThread extends Thread{

    private String LocalIP=null;
    // 创建一个 DatagramSocket 对象，不要有端口号，否则设置的端口被本类独占。

    /**
     * 
     * @param 构造函数传入的是本机IP地址
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
           //System.out.println("正在发送UDP广播到:"+s);

            
            // 创建一个 InetAddress
            InetAddress serverAddress = InetAddress.getByName(s);
            // 创建传输字符串
            String str = "2010级数字逻辑#测试员#专业必修#2分#30课时"+"#"+LocalIP;
            // 转换成字节数组
            byte data[] = str.getBytes("utf-8");

            // 创建以供 DatagramPacket 对象， 指定其发送地址和端口号
            DatagramPacket packet = new DatagramPacket(data, data.length,
                    serverAddress, 4567);

            // 调用 socket 对象的 send()方法发送数据
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
