package main;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread  {
	   
	    //���嵱ǰ�߳��������Socket
		Socket s = null;
		//������̶߳�Ӧѧ����ѧ��
		String id = null;
		//���߳��������Socket����Ӧ��������
		BufferedReader br = null;
		//���߳��������Socket����Ӧ�������
	    OutputStream os; 
	    String content=null;
	    clientmsg msg =null;
	    	    
		public ServerThread(Socket s)
			throws IOException
		{	
			System.out.println("asd");
			this.s = s;			
			//��ʼ����Socket��Ӧ��������
			br = new BufferedReader(new InputStreamReader(
				s.getInputStream() , "utf-8"));
			//��ʼ����Socket��Ӧ�������
			os = s.getOutputStream();
			
			}
	@Override
	public void run() {

		try {
		  givetoclient("�ѽ�������\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(msg==null&&(content=br.readLine())!=null){
				msg = new clientmsg(content);
				Socketinitialize.allmsg.add(msg);
				System.out.print("�������Ϣͨ��");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			while((content=br.readLine())!=null){
				this.msg.getnews(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public String getfromclient(){
		try {
			//��ȡ�ͻ��˷�������Ϣ
			
			if((content=br.readLine())!=null)
				return content;
			else 
				return null;
			}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
    public void givetoclient(String content) throws UnsupportedEncodingException, IOException
    {
    	os.write((content+"\n").getBytes("utf-8"));
    }
   
}
