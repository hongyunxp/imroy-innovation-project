package com.My.OsangProject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;


public class OsangProject2 extends TabActivity {
    TextView textView_id,textView_wlanName,textView_className,
    textView_teacherName,textView_classCategory,textView_credit,
    textView_classTime;
    
    public CheckBox checkBox_a,
            checkBox_b,
            checkBox_c,
            checkBox_d,
            checkBox_e,
            checkBox_waiver;
    
    boolean com_permission = false,
            vote_permission = false,
            exam_permission = false;
    
    Button btn_send,btn_voteSend;
	TextView textView_comPermission,textView_votePermission;
	
	int intChoiceNum;
	boolean isMultiChoice,isWaiverable;
	
    OutputStream os;
    Handler handler;
    Socket s;
    String IP = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
	    String class_info = null;
	    super.onCreate(savedInstanceState);

        Intent intent =getIntent();
        Bundle data = intent.getExtras();
        class_info = data.getString("class_info");
        final String[] arr_classInfo =class_info.split("#");

		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main2, tabHost.getTabContentView(),true);
		tabHost.addTab(tabHost.newTabSpec("att")
				.setIndicator("签到"/*,getResources().getDrawable(R.drawable.i3)*/)
				.setContent(R.id.att));
		tabHost.addTab(tabHost.newTabSpec("communicate")
				.setIndicator("交流"/*,getResources().getDrawable(R.drawable.i2)*/)
				.setContent(R.id.commnunication));
	      tabHost.addTab(tabHost.newTabSpec("vote")
	                .setIndicator("投票统计"/*,getResources().getDrawable(R.drawable.i2)*/)
	                .setContent(R.id.relativeLayout_vote));
	     
		 
		
//签到*******************************************************************
		textView_id = (TextView)findViewById(R.id.textView_id);
		textView_wlanName = (TextView)findViewById(R.id.textView_wlanName);
		textView_className = (TextView)findViewById(R.id.textView_className);
	    textView_teacherName = (TextView)findViewById(R.id.textView_teacherName);
	    textView_classCategory = (TextView)findViewById(R.id.textView_classCategory);
	    textView_credit = (TextView)findViewById(R.id.textView_credit);
	    textView_classTime = (TextView)findViewById(R.id.textView_classTime);
		
	    textView_id.setText(arr_classInfo[0]);
	    textView_wlanName.setText(arr_classInfo[1]);
	    textView_className.setText(arr_classInfo[2]);
	    textView_teacherName.setText(arr_classInfo[3]);
	    textView_classCategory.setText(arr_classInfo[4]);
	    textView_credit.setText(arr_classInfo[5]);
	    textView_classTime.setText(arr_classInfo[6]);
	    IP = arr_classInfo[7];
	    
	    final Button button_exit = (Button)findViewById(R.id.btn_exit);
	    button_exit.setOnClickListener(new OnClickListener()
        {
        public void onClick(View v)
            {
            Intent intent = new Intent();
            intent.setClass(OsangProject2.this, OsangProjectActivity.class);
            startActivity(intent);
            OsangProject2.this.finish();
            }
        });
	    
	    final Button button_login = (Button)findViewById(R.id.btn_login);
	    button_login.setOnClickListener(new OnClickListener()
        {
        public void onClick(View v)
            {
            try
            {
               s = new Socket(IP,30000);
               // 客户端启动ClientThread线程不断读取来自服务器的数据
               new Thread(new ClientThread(s, handler)).start();
               button_login.setText("已经进入课堂");
               button_login.setEnabled(false);
               button_exit.setEnabled(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {   // 将用户在文本框内输入的内容写入网络
                os=s.getOutputStream();
                os.write((arr_classInfo[0]+"\r\n").getBytes("utf-8"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            }
        });

//课堂交流*******************************************************************	    
   
	    textView_comPermission = (TextView)findViewById(R.id.permission);
		textView_comPermission.setTextColor(Color.RED);
		final EditText msg = (EditText)findViewById(R.id.editView_show);
		
	    final Button btn_send = (Button)findViewById(R.id.btn_send);
	        btn_send.setEnabled(false);
		btn_send.setOnClickListener(new OnClickListener()
        {
        public void onClick(View v)
            {
            try {
                os=s.getOutputStream();
                os.write((msg.getText().toString()+"\r\n").getBytes("utf-8"));
                msg.setText("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
//投票统计*******************************************************************
		checkBox_a = (CheckBox)findViewById(R.id.checkBox_a);
		checkBox_b = (CheckBox)findViewById(R.id.checkBox_b);
		checkBox_c = (CheckBox)findViewById(R.id.checkBox_c);
		checkBox_d = (CheckBox)findViewById(R.id.checkBox_d);
		checkBox_e = (CheckBox)findViewById(R.id.checkBox_e);
		checkBox_waiver = (CheckBox)findViewById(R.id.checkBox_waiver);
		checkBox_c.setVisibility(1);
        checkBox_d.setVisibility(1);
        checkBox_e.setVisibility(1);
		btn_voteSend = (Button)findViewById(R.id.button_voteSend);
		btn_voteSend.setEnabled(false);
		textView_votePermission = (TextView)findViewById(R.id.textView_votePermission);
		textView_votePermission.setTextColor(Color.RED);
		
		handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 如果消息来自于子线程
                if (msg.what == 0x123)
                {
                    String strMsg = msg.obj.toString();
                    //开启交流功能
                    if(strMsg.equals("comON")){
                        com_permission=true;
                        textView_comPermission.setText("-交流功能已开启，请控制每次发言内容在50字内-");
                        textView_comPermission.setTextColor(Color.GREEN);
                        btn_send.setEnabled(true);
                    }
                    //开启投票功能
                    //PC端发送的信息格式：voteON,选项数目,可否多选,可否弃权
                    if(strMsg.startsWith("voteON")){
                        vote_permission=true;
                        String[] temMsg=strMsg.split(",");
                        intChoiceNum=Integer.valueOf(temMsg[1]);
                        textView_votePermission.setText("-已开启投票功能-\r\n"+intChoiceNum+"选项 "+temMsg[2]);
                        textView_votePermission.setTextColor(Color.GREEN);
                        btn_voteSend.setEnabled(true);                       
                        
                        if(temMsg[2].equals("可多选"))
                            isMultiChoice=true;
                        else isMultiChoice =false;
                        
                        if(temMsg[3].equals("可弃权"))
                            isWaiverable=true;
                        else isWaiverable=false;
                        switch(intChoiceNum){
                            case 2:
                                checkBox_c.setVisibility(1);
                                checkBox_d.setVisibility(1);
                                checkBox_e.setVisibility(1);
                            case 3:
                                checkBox_d.setVisibility(1);
                                checkBox_e.setVisibility(1);
                            case 4:
                                checkBox_e.setVisibility(1);
                        }
                        if(!isWaiverable)
                            checkBox_waiver.setVisibility(1);
                    }
                    //开启测试功能
                    if(strMsg.equals("examON")){
                        exam_permission=true;
                    }
                    //关闭交流功能
                    if(strMsg.equals("comOFF")){
                        com_permission=false;
                        textView_comPermission.setText("-交流功能已关闭，请等待-");
                        textView_comPermission.setTextColor(Color.RED);
                        btn_send.setEnabled(false);
                    }
                    //关闭投票功能
                    if(strMsg.equals("voteOFF")){
                        com_permission=false;
                    }
                    //关闭测试功能
                    if(strMsg.equals("examOFF")){
                        com_permission=false;
                    }
                    
                    }
            }
        };
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
/**
 * 屏蔽返回实体键
 */
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
/*	            Intent intent = new Intent();
	            intent.setClass(OsangProject2.this, OsangProjectActivity.class);
	            startActivity(intent);
	            OsangProject2.this.finish();
	        case KeyEvent.KEYCODE_ENTER:*/
	            
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
