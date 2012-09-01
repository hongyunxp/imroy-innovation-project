package main;

import java.awt.Color;
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
	    OutputStream os = null; 
	    
	    String content=null;
	    clientMsg msg =null;
	    	    
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
				    Socketinitialize.allmsg.remove(i);//����Ѵ��ڸ�id��clientMsg,��ɾ��ԭcmsg,�����µ�
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
				/*System.out.print("�������Ϣͨ��");*/
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
                                "���ѵ�",
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
                        MainFrame.show.setText("���ÿ�����Ϣ�����ı䣬��ע�Ᵽ��");
                        MainFrame.menuItem_2.setEnabled(true);
                    }
                }
            }
        }
            else
                System.out.println("������Ϊ�Ƿ�����");
		
		
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
