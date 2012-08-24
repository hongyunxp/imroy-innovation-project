package com.My.OsangProject;



import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class OsangProject2 extends TabActivity {
    TextView textView_id,textView_wlanName,textView_className,
    textView_teacherName,textView_classCategory,textView_credit,
    textView_classTime;
    
    boolean com_permission=false,
            vote_permission=false,
            exam_permission=false;
    Button btn_send;
	TextView textView_comPermission;
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
		
		 final Button btn_send = (Button)findViewById(R.id.btn_send);
		 btn_send.setEnabled(false);
		 handler = new Handler()
	        {
	            @Override
	            public void handleMessage(Message msg)
	            {
	                // 如果消息来自于子线程
	                if (msg.what == 0x123)
	                {
	                    String strMsg = msg.obj.toString();
	                    if(strMsg.equals("comON")){
	                        com_permission=true;
	                        textView_comPermission.setText("-交流功能已开启，请控制每次发言内容在50字内-");
	                        textView_comPermission.setTextColor(Color.GREEN);
	                        btn_send.setEnabled(true);
	                    }
	                    if(strMsg.equals("voteON")){
	                        vote_permission=true;
	                    }
	                    if(strMsg.equals("examON")){
	                        exam_permission=true;
	                    }
	                    if(strMsg.equals("comOFF")){
	                        com_permission=false;
	                        textView_comPermission.setText("-交流功能已关闭，请等待-");
	                        textView_comPermission.setTextColor(Color.RED);
	                        btn_send.setEnabled(false);
	                    }
	                    if(strMsg.equals("voteOFF")){
	                        com_permission=false;
	                    }
	                    if(strMsg.equals("examOFF")){
	                        com_permission=false;
	                    }
	                    }
	            }
	        };
		
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
		
		btn_send.setOnClickListener(new OnClickListener()
        {
        public void onClick(View v)
            {
            try {
                os=s.getOutputStream();
                os.write((msg.getText().toString()+"\r\n").getBytes("utf-8"));
                msg.setText("");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
/*		menu.add(0, 0, 0, "关于");
		menu.add(0, 1, 0, "注销");*/
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case 0:
		{
			//开始
			Intent intent2 = new Intent();
			intent2.setClass(OsangProject2.this, About.class);
			startActivity(intent2);
			break;
		}
		case 1:
		{
			//结束
			
			OsangProject2.this.finish();
			
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
/**
 * 屏蔽返回实体键
 */
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
	            Intent intent = new Intent();
	            intent.setClass(OsangProject2.this, OsangProjectActivity.class);
	            startActivity(intent);
	            OsangProject2.this.finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}






