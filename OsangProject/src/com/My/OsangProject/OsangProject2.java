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
				.setIndicator("ǩ��"/*,getResources().getDrawable(R.drawable.i3)*/)
				.setContent(R.id.att));
		tabHost.addTab(tabHost.newTabSpec("communicate")
				.setIndicator("����"/*,getResources().getDrawable(R.drawable.i2)*/)
				.setContent(R.id.commnunication));
	      tabHost.addTab(tabHost.newTabSpec("vote")
	                .setIndicator("ͶƱͳ��"/*,getResources().getDrawable(R.drawable.i2)*/)
	                .setContent(R.id.relativeLayout_vote));
	     
		 
		
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
               // �ͻ�������ClientThread�̲߳��϶�ȡ���Է�����������
               new Thread(new ClientThread(s, handler)).start();
               button_login.setText("�Ѿ��������");
               button_login.setEnabled(false);
               button_exit.setEnabled(true);
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
//ͶƱͳ��*******************************************************************
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
                // �����Ϣ���������߳�
                if (msg.what == 0x123)
                {
                    String strMsg = msg.obj.toString();
                    //������������
                    if(strMsg.equals("comON")){
                        com_permission=true;
                        textView_comPermission.setText("-���������ѿ����������ÿ�η���������50����-");
                        textView_comPermission.setTextColor(Color.GREEN);
                        btn_send.setEnabled(true);
                    }
                    //����ͶƱ����
                    //PC�˷��͵���Ϣ��ʽ��voteON,ѡ����Ŀ,�ɷ��ѡ,�ɷ���Ȩ
                    if(strMsg.startsWith("voteON")){
                        vote_permission=true;
                        String[] temMsg=strMsg.split(",");
                        intChoiceNum=Integer.valueOf(temMsg[1]);
                        textView_votePermission.setText("-�ѿ���ͶƱ����-\r\n"+intChoiceNum+"ѡ�� "+temMsg[2]);
                        textView_votePermission.setTextColor(Color.GREEN);
                        btn_voteSend.setEnabled(true);                       
                        
                        if(temMsg[2].equals("�ɶ�ѡ"))
                            isMultiChoice=true;
                        else isMultiChoice =false;
                        
                        if(temMsg[3].equals("����Ȩ"))
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
                    //�������Թ���
                    if(strMsg.equals("examON")){
                        exam_permission=true;
                    }
                    //�رս�������
                    if(strMsg.equals("comOFF")){
                        com_permission=false;
                        textView_comPermission.setText("-���������ѹرգ���ȴ�-");
                        textView_comPermission.setTextColor(Color.RED);
                        btn_send.setEnabled(false);
                    }
                    //�ر�ͶƱ����
                    if(strMsg.equals("voteOFF")){
                        com_permission=false;
                    }
                    //�رղ��Թ���
                    if(strMsg.equals("examOFF")){
                        com_permission=false;
                    }
                    
                    }
            }
        };
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
/**
 * ���η���ʵ���
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
