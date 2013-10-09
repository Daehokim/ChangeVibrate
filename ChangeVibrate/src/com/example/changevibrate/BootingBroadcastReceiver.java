package com.example.changevibrate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootingBroadcastReceiver extends BroadcastReceiver {
	

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(arg0, "Booting complete", 0).show();
		Log.d("intent", "intent booting complate");
		


		Intent intent = new Intent(arg0, ChangeVibrateBroadcastReceiver.class);
		intent.putExtra(MainActivity.MSG, MainActivity.START);
		
		arg0.sendBroadcast(intent);
	}
}
