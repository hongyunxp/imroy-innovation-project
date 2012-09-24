package com.project.ECAT;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ECAuxiliaryToolActivity extends Activity {

    Button confirm,exit,check_wlan;
	EditText name;
    static TextView name_wlan;
	static boolean isUDPReceived = false;
	static String udp=null;
	String id=null;
    String wlan_name=null;
    
	Dialog myPrepare;

	// 定义WifiManager对象
    private WifiManager mWifiManager;
	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        name = (EditText)findViewById(R.id.editView_id);
        name_wlan = (TextView)findViewById(R.id.textView_show_wlanId);
        confirm = (Button)findViewById(R.id.button_confirm);
        check_wlan = (Button)findViewById(R.id.button_checkWlan);
        exit = (Button)findViewById(R.id.button_exit);

        final android.app.AlertDialog.Builder prepare = new AlertDialog.Builder(this);

       mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {  
        	name_wlan.setText("当前尚未连入WLAN");
        	}
        else{
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        if(wifiInfo.getSSID()!=null){
            name_wlan.setText(wifiInfo.getSSID());
            wlan_name=wifiInfo.getSSID();
            }
        else
        	name_wlan.setText("当前尚未连入WLAN");
        }

        check_wlan.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		
			{
			
			startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
			
			}
		});

        confirm.setOnClickListener(new OnClickListener()
		{
		@SuppressWarnings("unchecked")
        public void onClick(View v)
			{
			
			if(name.getText().toString().length()!=0 && wlan_name!=null){
			    id= name.getText().toString();
				RelativeLayout prepare_Layout = (RelativeLayout)getLayoutInflater()
						.inflate(R.layout.prepare_alert_dialog,null);
				prepare.setView(prepare_Layout);
				myPrepare = prepare.create();
				myPrepare.show();
				ReceiveUDP_THREAD rTask = null;
                
				rTask = new ReceiveUDP_THREAD();
	            rTask.execute();
				
			}
			}
		});

        exit.setOnClickListener(new OnClickListener()
		{			
			public void onClick(View v)
			{
			    /*完全关闭并推出程序，仅有finish（）的话只能实现销毁当前activity*/
				ECAuxiliaryToolActivity.this.finish();
				System.exit(0); 
			}
		});
        
    }
    
    @Override
    public void onResume(){

        super.onResume();
        
        id=null;
        wlan_name=null;
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {  
        	name_wlan.setText("当前尚未连入WLAN");
        }
        else{
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        if(wifiInfo.getSSID()!=null){
            name_wlan.setText(wifiInfo.getSSID());
        	wlan_name=wifiInfo.getSSID(); 
        }
        else
        	name_wlan.setText("当前尚未连入WLAN");
        }
    }
    
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "关于");
		menu.add(0, 1, 0, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case 0:
		{
			
			Intent intent1 = new Intent();
			intent1.setClass(ECAuxiliaryToolActivity.this, About.class);
			startActivity(intent1);
			break;
		}
		case 1:
		{
			
		    android.os.Process.killProcess(android.os.Process.myPid()); 
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	@SuppressWarnings("rawtypes")
    public class ReceiveUDP_THREAD extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
		    String result=null;
		try
             {
			            DatagramSocket socket = new DatagramSocket(4567);

			            byte data[] = new byte[1024];
			            // 创建一个空 DatagramPacket 对象
			            DatagramPacket packet = new DatagramPacket(data, data.length);
			            // 使用 receiver 方法接收客户端所发送到数据， 如果客户端没有发送数据， 进程阻塞
			            socket.setSoTimeout(10000);
			            socket.receive(packet);
			            result = new String(packet.getData(), packet.getOffset(),
			                    packet.getLength());
			            udp=result;
			             
			             myPrepare.dismiss();

			             Bundle class_infor = new Bundle();
			             class_infor.putString("class_info",id+"#"+wlan_name+"#"+result);
			             Intent intent = new Intent();
			             intent.putExtras(class_infor);
			             intent.setClass(ECAuxiliaryToolActivity.this, MainActivity.class);
			             startActivity(intent);
			             ECAuxiliaryToolActivity.this.finish();

			        }
			        catch (SocketException e)
			        {
			            myPrepare.dismiss();
			        }
			        catch (IOException e)
			        {
			            e.printStackTrace();
			        }

			return result;
		}
		
        @SuppressWarnings("unchecked")
        protected void onProgressUpdate(Integer... progress) {  
             super.onProgressUpdate(progress);
        }  
		

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    /**
	     * 屏蔽返回实体键
	     */
	            switch (keyCode) {
	                case KeyEvent.KEYCODE_ENTER:
	                return true;
	            }
	            return super.onKeyDown(keyCode, event);
	        }
	
    
}
