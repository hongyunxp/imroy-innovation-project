package com.My.OsangProject;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;


public class OsangProject2 extends TabActivity {
	TextView permission;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		/*TextView commu_permission = (TextView)findViewById(R.id.permission);
		commu_permission.setTextColor(Color.RED);*/
	    
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main2, tabHost.getTabContentView(),true);
		tabHost.addTab(tabHost.newTabSpec("att")
				.setIndicator("签到"/*,getResources().getDrawable(R.drawable.i3)*/)
				.setContent(R.id.att));
		tabHost.addTab(tabHost.newTabSpec("text2")
				.setIndicator("交流"/*,getResources().getDrawable(R.drawable.i2)*/)
				.setContent(R.id.commnunication));
		
		permission = (TextView)findViewById(R.id.permission);
		permission.setTextColor(Color.RED);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "关于");
		menu.add(0, 1, 0, "注销");
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
}






