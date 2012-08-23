package com.My.OsangProject;



import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class OsangProject2 extends TabActivity {
    TextView textView_id,textView_wlanName,textView_className,
    textView_teacherName,textView_classCategory,textView_credit,
    textView_classTime;
    
	TextView permission;
    OutputStream os;
    Handler handler;
    Socket s;
    String IP = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		/*TextView commu_permission = (TextView)findViewById(R.id.permission);
		commu_permission.setTextColor(Color.RED);*/
        
        String class_info = null;
        
		super.onCreate(savedInstanceState);

        Intent intent =getIntent();
        Bundle data = intent.getExtras();
        class_info = data.getString("class_info");
        final String[] arr_classInfo =class_info.split("#");


        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // �����Ϣ���������߳�
                if (msg.what == 0x123)
                {
                    // ����ȡ������׷����ʾ���ı�����
                    //show.append("\n" + msg.obj.toString());
                }
            }
        };

		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main2, tabHost.getTabContentView(),true);
		tabHost.addTab(tabHost.newTabSpec("att")
				.setIndicator("ǩ��"/*,getResources().getDrawable(R.drawable.i3)*/)
				.setContent(R.id.att));
		tabHost.addTab(tabHost.newTabSpec("communicate")
				.setIndicator("����"/*,getResources().getDrawable(R.drawable.i2)*/)
				.setContent(R.id.commnunication));
		
		//ǩ��*******************************************************************
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
	    
	    
	    Button button_login = (Button)findViewById(R.id.btn_login);
	    button_login.setOnClickListener(new OnClickListener()
        {
        public void onClick(View v)
            {
            try
            {
               s = new Socket(IP,30000);
               // �ͻ�������ClientThread�̲߳��϶�ȡ���Է�����������
               new Thread(new ClientThread(s, handler)).start();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {   // ���û����ı��������������д������
                
                os=s.getOutputStream();
                os.write((arr_classInfo[0]+"\r\n").getBytes("utf-8"));
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            }
        });

	    //���ý���*******************************************************************	    
		permission = (TextView)findViewById(R.id.permission);
		permission.setTextColor(Color.RED);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "����");
		menu.add(0, 1, 0, "ע��");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case 0:
		{
			//��ʼ
			Intent intent2 = new Intent();
			intent2.setClass(OsangProject2.this, About.class);
			startActivity(intent2);
			break;
		}
		case 1:
		{
			//����
			
			OsangProject2.this.finish();
			
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
}






