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
	  for(clientMsg msg:Socketinitialize.allmsg){
		  if(msg.havenew){
			  
		  Question.show.append(msg.getid()+":\r\n");
		  Question.show.append(msg.sendNews()+"\r\n");
		  Question.show.append("\r\n");
		  Question.show.append("\r\n");
		  
		  }
	   }
   }
  }
   
   
else {Question.show.append("暂时没有连接\n");
}
   
   
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


