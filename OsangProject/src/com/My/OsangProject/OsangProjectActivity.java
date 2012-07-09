package com.My.OsangProject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
	static String id=null;
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        name = (EditText)findViewById(R.id.editView_id);
        name_wlan = (TextView)findViewById(R.id.textView_show_wlanId);
        confirm = (Button)findViewById(R.id.button_confirm);
        check_wlan = (Button)findViewById(R.id.button_checkWlan);
        
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
			{if(name.getTextSize()!=0){
				id= name.getText().toString();
				Intent intent = new Intent();
				intent.setClass(OsangProjectActivity.this, OsangProject2.class);
				startActivity(intent);
				
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
    
}
