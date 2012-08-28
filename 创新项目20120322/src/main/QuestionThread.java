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

   Question.show.setText("----------------------准备开始课堂交流\r\n");
   if(Socketinitialize.allthread.size()!=0){
   while(true){
	   //System.out.println(Socketinitialize.allmsg.size());
	  for(clientMsg msg:Socketinitialize.allmsg){
		  if(msg.havenew){
		      Calendar cal = Calendar.getInstance();
	          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
	          String str = sdf.format(cal.getTime());
		  Question.show.append(msg.getid()+"  "+str+"\r\n");
		  Question.show.append(msg.sendNews()+"\r\n");
		  Question.show.append("\r\n");
		  Question.show.setCaretPosition( Question.show.getText().length());
		  }
	   }
   }
  }
   
   
else {Question.show.append("----------------------暂时没有连接\n");
}
   
   
}


}


