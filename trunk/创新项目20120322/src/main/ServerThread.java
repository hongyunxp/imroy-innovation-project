package main;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread  {
	   
	    //定义当前线程所处理的Socket
		Socket s = null;
		
		//定义该线程对应学生的学号
		String id = null;
		
		//该线程所处理的Socket所对应的输入流
		BufferedReader br = null;
		
		//该线程所处理的Socket所对应的输出流
	    OutputStream os; 
	    
	    String content=null;
	    clientmsg msg =null;
	    	    
		public ServerThread(Socket s)
			throws IOException
		{	
			System.out.println("进入建立连接线程");
			this.s = s;			
			
			//初始化该Socket对应的输入流
			br = new BufferedReader(new InputStreamReader(
				s.getInputStream() , "utf-8"));
			
			//初始化该Socket对应的输出流
			os = s.getOutputStream();
			
			}
	@Override
	public void run() {

		try {
		  givetoclient("已建立连接\r\n");
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
				/*System.out.print("已完成信息通道");*/
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
