package com.example.changevibrate;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	public static final String VIBRATE_MODE = "vibrate_mode";
	public static final String SET = "set";
	public static final String HOUR = "hour";
	public static final String MIN = "min";
	public static final String Cal = "calendar";
	public static final String MSG = "message";
	public static final String ACTION = "change_action";
	public static final String START = "alarm_start";
	
	private Button sendBroadCastBtn;


	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sendBroadCastBtn = (Button) findViewById(R.id.button1);
        sendBroadCastBtn.setOnClickListener(this);
        
        Intent intent = new Intent(this, ChangeVibrateBroadcastReceiver.class);
        intent.putExtra(MSG, START);
        
        sendBroadcast(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == sendBroadCastBtn) {
			ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
			for (RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
				Log.d("intent", "intent service : " + runningServiceInfo.service.getClassName());
			}
		}
	}
}
