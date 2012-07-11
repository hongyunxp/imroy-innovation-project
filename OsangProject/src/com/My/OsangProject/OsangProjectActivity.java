package com.My.OsangProject;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentProviderOperation.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class OsangProjectActivity extends Activity {
	// 定义WifiManager对象
    private WifiManager mWifiManager;
	// 定义WifiInfo对象
    private WifiInfo mWifiInfo;
    
    private final static String TAG = "WifiAdmin";
    
	Button confirm,exit,check_wlan;
	EditText name;
	TextView name_wlan;
	static boolean isUDPReceived = false;
	static String id=null;
	Dialog myPrepare;
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        name = (EditText)findViewById(R.id.editView_id);
        name_wlan = (TextView)findViewById(R.id.textView_show_wlanId);
        confirm = (Button)findViewById(R.id.button_confirm);
        check_wlan = (Button)findViewById(R.id.button_checkWlan);
        final android.app.AlertDialog.Builder prepare = new AlertDialog.Builder(this);
		
        
        
       mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {  
        	name_wlan.setText("当前尚未连入WLAN");
        }
        else{
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        if(wifiInfo.getSSID()!=null)
        	     name_wlan.setText(wifiInfo.getSSID());
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
		public void onClick(View v)
			{
			
			if(name.getText().toString().length()!=0){

				RelativeLayout prepare_Layout = (RelativeLayout)getLayoutInflater()
						.inflate(R.layout.prepare_alert_dialog,null);
				prepare.setView(prepare_Layout);
				myPrepare = prepare.create();
				myPrepare.show();

				ReceiveUDP_THREAD rTask = null;
				rTask = new ReceiveUDP_THREAD();
	            rTask.execute();  
/*	       try
             {
			            DatagramSocket socket = new DatagramSocket(4567);

			            byte data[] = new byte[1024];
			            // 创建一个空 DatagramPacket 对象
			            DatagramPacket packet = new DatagramPacket(data, data.length);

			            // 使用 receiver 方法接收客户端所发送到数据， 如果客户端没有发送数据， 进程阻塞
			            socket.setSoTimeout(900);
			            socket.receive(packet);
			            String result = new String(packet.getData(), packet.getOffset(),
			                    packet.getLength());

			        }
			        catch (SocketException e)
			        {
			            e.printStackTrace();
			        }
			        catch (IOException e)
			        {
			            e.printStackTrace();
			        }*/

			
				/*id= name.getText().toString();
				Intent intent = new Intent();
				intent.setClass(OsangProjectActivity.this, OsangProject2.class);
				startActivity(intent);*/
                
				//myPrepare.dismiss();
				
				
			}
			}
		});
        
        
        exit = (Button)findViewById(R.id.button_exit);
        exit.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				OsangProjectActivity.this.finish();
			}
		});
        
    }
    
    @Override
    public void onResume(){
    	super.onResume();

        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {  
        	name_wlan.setText("当前尚未连入WLAN");
        }
        else{
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        if(wifiInfo.getSSID()!=null)
        	     name_wlan.setText(wifiInfo.getSSID());
        else
        	name_wlan.setText("当前尚未连入WLAN");
        }
    	
    }
    
	@Override
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
			intent1.setClass(OsangProjectActivity.this, About.class);
			startActivity(intent1);
			break;
		}
		case 1:
		{
			
			OsangProjectActivity.this.finish();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class ReceiveUDP_THREAD extends AsyncTask{


		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
		try
             {
			            DatagramSocket socket = new DatagramSocket(4567);

			            byte data[] = new byte[1024];
			            // 创建一个空 DatagramPacket 对象
			            DatagramPacket packet = new DatagramPacket(data, data.length);

			            // 使用 receiver 方法接收客户端所发送到数据， 如果客户端没有发送数据， 进程阻塞
			            socket.setSoTimeout(5000);
			            socket.receive(packet);
			            String result = new String(packet.getData(), packet.getOffset(),
			                    packet.getLength());

			        }
			        catch (SocketException e)
			        {
			            e.printStackTrace();
			        }
			        catch (IOException e)
			        {
			            e.printStackTrace();
			        }
			
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			myPrepare.dismiss();
			isUDPReceived=true;
			id= name.getText().toString();
			Intent intent = new Intent();
			intent.setClass(OsangProjectActivity.this, OsangProject2.class);
			startActivity(intent);
			return null;
		}
		

	}
    
}
