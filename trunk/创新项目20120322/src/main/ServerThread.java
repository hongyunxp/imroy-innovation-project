package main;

import java.awt.Color;
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
	    OutputStream os = null; 
	    
	    String content=null;
	    clientMsg msg =null;
	    	    
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
		  givetoclient("comOFF");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
		    while(msg==null&&(content=br.readLine())!=null){
				boolean cmsg_creat_finish=false;
				for(int i=0;i<Socketinitialize.allmsg.size();i++){
					if(Socketinitialize.allmsg.get(i).getid().equals(content)){
				    Socketinitialize.allmsg.remove(i);//如果已存在该id的clientMsg,则删除原cmsg,建立新的
					msg = new clientMsg(content);
					Socketinitialize.allmsg.add(msg);
					cmsg_creat_finish=true;
					break;
					}
				}
			if(cmsg_creat_finish==false){					
				/*System.out.println(content);*/
				msg = new clientMsg(content);
				Socketinitialize.allmsg.add(msg);
				/*System.out.print("已完成信息通道");*/
			}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(Socketinitialize.allmsg.size()!=0){
            for(clientMsg msg:Socketinitialize.allmsg){
                for(int i=0;i<MainFrame.inforshow.getRowCount();i++){
                    if(msg.id.equals(MainFrame.inforshow.getValueAt(i,1).toString())){
                        String[]tem={
                                "●已到",
                                MainFrame.inforshow.getValueAt(i,1).toString(),
                                MainFrame.inforshow.getValueAt(i,2).toString(),
                                MainFrame.inforshow.getValueAt(i,3).toString(),
                                MainFrame.inforshow.getValueAt(i,4).toString(),
                        };
                        MainFrame.att_tableModel.addRow(tem);
                        MainFrame.unatt_tableModel.removeRow(i);
                        for(Studentinfor stuinfor:MainFrame.allstudents){
                            if(stuinfor.id.equals(MainFrame.inforshow.getValueAt(i,1).toString())){
                                stuinfor.att_time++;
                            }
                        }
                        MainFrame.show.setForeground(Color.RED);
                        MainFrame.show.setText("课堂考勤信息发生改变，请注意保存");
                        MainFrame.menuItem_2.setEnabled(true);
                    }
                }
            }
        }
            else
                System.out.println("此连接为非法连接");
		
		
		try {
			while((content=br.readLine())!=null){
			    if(content.startsWith("oxvote"))
			        this.msg.getNewVote(content.substring(6));
			        else{
			            this.msg.getNews(content);
		                System.out.println(content);
		                }
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
