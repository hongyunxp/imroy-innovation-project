package com.My.OsangProject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class OsangProjectActivity extends Activity {
	// ����WifiManager����
    private WifiManager mWifiManager;
	// ����WifiInfo����
    private WifiInfo mWifiInfo;
	Button confirm,Button2;
	EditText name;
	TextView name_wlan;
	static String id=null;
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        name = (EditText)findViewById(R.id.username);
        name_wlan = (TextView)findViewById(R.id.wlanid);
        confirm = (Button)findViewById(R.id.Button1);
        
       mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {  
            mWifiManager.setWifiEnabled(true);  
        }  
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        name_wlan.setText(wifiInfo.getSSID());
        

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
        Button2 = (Button)findViewById(R.id.Button2);
        Button2.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				OsangProjectActivity.this.finish();
			}
		});
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "����");
		menu.add(0, 1, 0, "�˳�");
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
