package main;

import java.io.*;
import java.net.*;

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
			System.out.println("���뽨�������߳�");
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
				boolean cmsg_creat_finish=false;
				for(int i=0;i<Socketinitialize.allmsg.size();i++){
					if(Socketinitialize.allmsg.get(i).getid().equals(content)){
				    Socketinitialize.allmsg.remove(i);
					msg = new clientmsg(content);
					Socketinitialize.allmsg.add(msg);
					cmsg_creat_finish=true;
					break;
					}
				}
			if(cmsg_creat_finish==false){					
				/*System.out.println(content);*/
				msg = new clientmsg(content);
				Socketinitialize.allmsg.add(msg);
				/*System.out.print("�������Ϣͨ��");*/
			}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			while((content=br.readLine())!=null){
				this.msg.getnews(content);
				System.out.println(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    public void givetoclient(String content) throws UnsupportedEncodingException, IOException
    {
    	os.write((content+"\r\n").getBytes("utf-8"));
    }
   
}
