package com.project.ECAT;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import com.My.OsangProject.R;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends TabActivity {
    TextView textView_id,textView_wlanName,textView_className,
    textView_teacherName,textView_classCategory,textView_credit,
    textView_classTime;
    
    EditText msg,editView_1to5,editView_6to10,editView_11to15,editView_16to20;
    
    public CheckBox checkBox_a,
            checkBox_b,
            checkBox_c,
            checkBox_d,
            checkBox_e,
            checkBox_waiver;
    
    boolean com_permission = false,
            vote_permission = false,
            exam_permission = false;
    
    Button btn_send,btn_voteSend,btn_examSend;
	TextView textView_comPermission,textView_votePermission,textView_examPermission;
	
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
				.setIndicator("签到",getResources().getDrawable(R.drawable.icon_att))
				.setContent(R.id.att));
		tabHost.addTab(tabHost.newTabSpec("communicate")
				.setIndicator("交流",getResources().getDrawable(R.drawable.icon_com))
				.setContent(R.id.commnunication));
	    tabHost.addTab(tabHost.newTabSpec("vote")
	                .setIndicator("投票统计",getResources().getDrawable(R.drawable.icon_vote))
	                .setContent(R.id.relativeLayout_vote));
	    tabHost.addTab(tabHost.newTabSpec("exam")
                .setIndicator("当堂测验",getResources().getDrawable(R.drawable.icon_vote))
                .setContent(R.id.relativeLayout_exam));
	     
		 
		
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
            intent.setClass(MainActivity.this, ECAuxiliaryToolActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
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
		
	    btn_send = (Button)findViewById(R.id.btn_send);
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
//************************************************************************
	    btn_voteSend = (Button)findViewById(R.id.button_voteSend);
	    
	    textView_votePermission = (TextView)findViewById(R.id.textView_votePermission);
	    
		checkBox_a = (CheckBox)findViewById(R.id.checkBox_a);
		checkBox_b = (CheckBox)findViewById(R.id.checkBox_b);
		checkBox_c = (CheckBox)findViewById(R.id.checkBox_c);
		checkBox_d = (CheckBox)findViewById(R.id.checkBox_d);
		checkBox_e = (CheckBox)findViewById(R.id.checkBox_e);
		checkBox_waiver = (CheckBox)findViewById(R.id.checkBox_waiver);
		
		btn_voteSend.setEnabled(false);
		
		textView_votePermission.setTextColor(Color.RED);
		
        checkBox_a.setEnabled(false);
        checkBox_b.setEnabled(false);
        checkBox_c.setEnabled(false);
        checkBox_d.setEnabled(false);
        checkBox_e.setEnabled(false);
        checkBox_waiver.setEnabled(false);
        checkBox_waiver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                if(isChecked){
                    checkBox_a.setChecked(false);
                    checkBox_a.setEnabled(false);
                    checkBox_b.setChecked(false);
                    checkBox_b.setEnabled(false);
                    checkBox_c.setChecked(false);
                    checkBox_c.setEnabled(false);
                    checkBox_d.setChecked(false);
                    checkBox_d.setEnabled(false);
                    checkBox_e.setChecked(false);
                    checkBox_e.setEnabled(false);
                }
                else{
                    checkBox_a.setEnabled(true);
                    checkBox_b.setEnabled(true);
                    checkBox_c.setEnabled(true);
                    checkBox_d.setEnabled(true);
                    checkBox_e.setEnabled(true);
                }
            }
        });
        
//当堂测验*******************************************************************
//************************************************************************
        btn_examSend = (Button)findViewById(R.id.button_examSend);
        btn_examSend.setEnabled(false);
        
        textView_examPermission = (TextView)findViewById(R.id.textView_examPermission);
        textView_examPermission.setTextColor(Color.RED);
        
        editView_1to5 = (EditText)findViewById(R.id.editView_1to5);
        editView_6to10 = (EditText)findViewById(R.id.editView_6to10);
        editView_11to15 = (EditText)findViewById(R.id.editView_11to15);
        editView_16to20 = (EditText)findViewById(R.id.editView_16to20);
        editView_1to5.setEnabled(false);
        editView_6to10.setEnabled(false);
        editView_11to15.setEnabled(false);
        editView_16to20.setEnabled(false);        
        
		handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 如果消息来自于子线程
                if (msg.what == 0x123)
                {
                    String strMsg = msg.obj.toString();
//开启交流功能*******************************************************************************************************
                    if(strMsg.equals("comON")){
                        com_permission=true;
                        textView_comPermission.setText("-交流功能已开启-");
                        textView_comPermission.setTextColor(Color.GREEN);
                        btn_send.setEnabled(true);
                    }
//开启投票功能*******************************************************************************************************
                    //PC端发送的信息格式：voteON,选项数目,可否多选（false/true）,可否弃权（false/true）
                    if(strMsg.startsWith("voteON")){
                        btn_voteSend.setEnabled(true);
                        
                        String[] temMsg=strMsg.split(",");
                        intChoiceNum=Integer.valueOf(temMsg[1]);
                        textView_votePermission.setText("-已开启投票功能-\r\n"+intChoiceNum+"选项 "+temMsg[2]);
                        textView_votePermission.setTextColor(Color.GREEN);
                        
                        switch(intChoiceNum){
                            case 2:
                                checkBox_c.setVisibility(View.GONE);
                                checkBox_d.setVisibility(View.GONE);
                                checkBox_e.setVisibility(View.GONE);
                                break;
                            case 3:
                                checkBox_d.setVisibility(View.GONE);
                                checkBox_e.setVisibility(View.GONE);
                                break;
                            case 4:
                                checkBox_e.setVisibility(View.GONE);
                                break;
                        }
                        
                        if(!temMsg[3].equals("可弃权"))
                            checkBox_waiver.setVisibility(View.GONE);
                        
                        checkBox_a.setChecked(false);
                        checkBox_a.setEnabled(true);
                        checkBox_b.setChecked(false);
                        checkBox_b.setEnabled(true);
                        checkBox_c.setChecked(false);
                        checkBox_c.setEnabled(true);
                        checkBox_d.setChecked(false);
                        checkBox_d.setEnabled(true);
                        checkBox_e.setChecked(false);
                        checkBox_e.setEnabled(true);
                        checkBox_waiver.setChecked(false);
                        checkBox_waiver.setEnabled(true);
                        
                        if(temMsg[2].equals("可多选")){
                            //可多选时
                            checkBox_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    
                                }
                            });
                            checkBox_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    
                                }
                            });
                            checkBox_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    
                                }
                            });
                            checkBox_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    
                                }
                            });
                            checkBox_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    
                                }
                            });
                            btn_voteSend.setOnClickListener(new OnClickListener(){
                                public void onClick(View v) {
                                    String strVoteContent="oxvote";
                                    if(checkBox_a.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="A";
                                    }
                                    else strVoteContent+="N";
                                    if(checkBox_b.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="B";
                                    }
                                    else strVoteContent+="N";
                                    if(checkBox_c.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="C";
                                    }
                                    else strVoteContent+="N";
                                    if(checkBox_d.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="D";
                                    }
                                    else strVoteContent+="N";
                                    if(checkBox_e.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="E";
                                    }
                                    else strVoteContent+="N";
                                    if(checkBox_waiver.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        strVoteContent+="X";
                                    }
                                    else strVoteContent+="N";
                                    
                                    if(!btn_voteSend.isEnabled()){
                                        checkBox_a.setVisibility(View.VISIBLE);
                                        checkBox_b.setVisibility(View.VISIBLE);
                                        checkBox_c.setVisibility(View.VISIBLE);
                                        checkBox_d.setVisibility(View.VISIBLE);
                                        checkBox_e.setVisibility(View.VISIBLE);
                                        checkBox_waiver.setVisibility(View.VISIBLE);
                                        checkBox_a.setEnabled(false);
                                        checkBox_b.setEnabled(false);
                                        checkBox_c.setEnabled(false);
                                        checkBox_d.setEnabled(false);
                                        checkBox_e.setEnabled(false);
                                        checkBox_waiver.setEnabled(false);
                                        checkBox_a.setChecked(false);
                                        checkBox_b.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);

                                        textView_votePermission.setText("-投票已提交，请等待-");
                                        textView_votePermission.setTextColor(Color.RED);

                                        textView_votePermission.setText("-投票已提交，请等待-");
                                        textView_votePermission.setTextColor(Color.RED);
                                        
                                        try {
                                            os=s.getOutputStream();
                                            os.write((strVoteContent+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            });
                            
                        }
                        else {
                            //不可多选时
                            checkBox_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    if(isChecked){
                                        checkBox_b.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                    }
                                }
                            });
                            checkBox_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    if(isChecked){
                                        checkBox_a.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                    }
                                }
                            });
                            checkBox_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    if(isChecked){
                                        checkBox_b.setChecked(false);
                                        checkBox_a.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                    }
                                }
                            });
                            checkBox_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    if(isChecked){
                                        checkBox_b.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_a.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                    }
                                }
                            });
                            checkBox_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                public void onCheckedChanged(CompoundButton buttonView,
                                        boolean isChecked) {
                                    if(isChecked){
                                        checkBox_b.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_a.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                    }
                                }
                            });
                            btn_voteSend.setOnClickListener(new OnClickListener(){
                                public void onClick(View v) {
                                    if(checkBox_a.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteANNNNN"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(checkBox_b.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteNBNNNN"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(checkBox_c.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteNNCNNN"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(checkBox_d.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteNNNDNN"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(checkBox_e.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteNNNNEN"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(checkBox_waiver.isChecked()){
                                        btn_voteSend.setEnabled(false);
                                        try {
                                            os=s.getOutputStream();
                                            os.write(("oxvoteNNNNNX"+"\r\n").getBytes("utf-8"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                    if(!btn_voteSend.isEnabled()){
                                        checkBox_a.setVisibility(View.VISIBLE);
                                        checkBox_b.setVisibility(View.VISIBLE);
                                        checkBox_c.setVisibility(View.VISIBLE);
                                        checkBox_d.setVisibility(View.VISIBLE);
                                        checkBox_e.setVisibility(View.VISIBLE);
                                        checkBox_waiver.setVisibility(View.VISIBLE);
                                        checkBox_a.setEnabled(false);
                                        checkBox_b.setEnabled(false);
                                        checkBox_c.setEnabled(false);
                                        checkBox_d.setEnabled(false);
                                        checkBox_e.setEnabled(false);
                                        checkBox_waiver.setEnabled(false);
                                        checkBox_a.setChecked(false);
                                        checkBox_b.setChecked(false);
                                        checkBox_c.setChecked(false);
                                        checkBox_d.setChecked(false);
                                        checkBox_e.setChecked(false);
                                        checkBox_waiver.setChecked(false);
                                        textView_votePermission.setText("-投票已提交，请等待-");
                                        textView_votePermission.setTextColor(Color.RED);
                                    }
                                }
                            });
                        }
                    }
//开启测试功能*******************************************************************************************************
                    if(strMsg.startsWith("examON")){
                        exam_permission=true;
                        final int intProblemNum;
                        int intChoiceNum;
                        String[] temStr = strMsg.split(",");
                        intProblemNum = Integer.valueOf(temStr[1]);
                        intChoiceNum = Integer.valueOf(temStr[2]);
                        
                        textView_examPermission.setText("-已开启当堂测验功能，共"+intProblemNum+"题-");
                        textView_examPermission.setTextColor(Color.GREEN);
                        
                        btn_examSend.setEnabled(true);
                        switch(intProblemNum){
                            case 5:
                                editView_1to5.setEnabled(true);
                            editView_6to10.setEnabled(false);
                            editView_11to15.setEnabled(false);
                            editView_16to20.setEnabled(false);
                            break;
                            case 10:
                                editView_1to5.setEnabled(true);
                                editView_6to10.setEnabled(true);
                                editView_11to15.setEnabled(false);
                            editView_16to20.setEnabled(false);
                            break;
                            case 15:
                                editView_1to5.setEnabled(true);
                                editView_6to10.setEnabled(true);
                                editView_11to15.setEnabled(true);
                                editView_16to20.setEnabled(false);
                            break;
                            case 20:
                                editView_1to5.setEnabled(true);
                                editView_6to10.setEnabled(true);
                                editView_11to15.setEnabled(true);
                                editView_16to20.setEnabled(true);
                                break;
                        }
                        btn_examSend.setOnClickListener(new OnClickListener(){
                            public void onClick(View v) {
                                String temAns="oxexam"+editView_1to5.getText().toString()+
                                        editView_1to5.getText().toString()+
                                        editView_11to15.getText().toString()+
                                        editView_16to20.getText().toString();
                                //if(temAns.length()==intProblemNum){
                                    try {
                                        os=s.getOutputStream();
                                        os.write((temAns+"\r\n").getBytes("utf-8"));
                                        btn_examSend.setEnabled(false);
                                        textView_examPermission.setText("答案已提交，请等待-");
                                        textView_examPermission.setTextColor(Color.RED);
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    editView_1to5.setText("");
                                    editView_1to5.setEnabled(false);
                                    editView_6to10.setText("");
                                    editView_6to10.setEnabled(false);
                                    editView_11to15.setText("");
                                    editView_11to15.setEnabled(false);
                                    editView_16to20.setText("");
                                    editView_16to20.setEnabled(false);
                                    
                                //}
                                
                            }
                        });
                        
                    }
//关闭交流功能*******************************************************************************************************
                    if(strMsg.equals("comOFF")){
                        com_permission=false;
                        textView_comPermission.setText("-交流功能已关闭，请等待-");
                        textView_comPermission.setTextColor(Color.RED);
                        btn_send.setEnabled(false);
                    }
//关闭投票功能*******************************************************************************************************
                    if(strMsg.equals("voteOFF")){
                        com_permission=false;
                        textView_votePermission.setText("-投票功能已关闭");
                        textView_votePermission.setTextColor(Color.RED);
                        btn_voteSend.setEnabled(false);  
                    }
//关闭测试功能*******************************************************************************************************
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
	            
	        return true;
	    }
	    
	    return super.onKeyDown(keyCode, event);
	}
}
