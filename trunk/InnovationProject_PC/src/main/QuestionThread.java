package main;

import java.net.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class QuestionThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
   Question.show.setText("准备开始进行发言......\r\n");
   if(Socketinitialize.allthread.size()!=0){
   while(true){
	   System.out.println(Socketinitialize.allmsg.size());
	  for(clientmsg msg:Socketinitialize.allmsg){
		  if(msg.havenew){
		  Question.show.append(msg.id+":\r\n");
		  Question.show.append(msg.sendnews()+"\r\n");
		  Question.show.append("--------------------------------------------\r\n");
		  }
	   }
	/*int i=0;
	while(i<Socketinitialize.allthread.size()){
		//Question.show.append(Socketinitialize.allthread.get(i).id+":\r\n");
		Question.show.append(Socketinitialize.allthread.get(i).getfromclient()+"\r\n");
		//Question.show.append(datetime()+":\r\n");
		i++;
	}*/
   }
  }
else {Question.show.append("10102510130 发言：\n");
   Question.show.append("已建立连接。\n");
   Question.show.append("2012/3/11 23:42-----------------\n");}
}
/*	public String datetime(){
	        SimpleDateFormat ft=null;
	        Date date=null;
	        Calendar cl= Calendar.getInstance();
	        cl.setTime(new java.util.Date());
	        date=(Date) cl.getTime();
	       
	        ft=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        String dateTime = ft.format(date);
	        return dateTime;      
	}*/

}


