package com.example.changevibrate;

import java.util.Calendar;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class ChangeVibrateBroadcastReceiver extends BroadcastReceiver {
	private int baseHour = 23;



	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		String msg = arg1.getStringExtra(MainActivity.MSG);
		Log.d("Intent", "intent : " + msg);
		if(msg.equals(MainActivity.START)) {
			Intent intent = new Intent(arg0, ChangeVibrateBroadcastReceiver.class);
			intent.putExtra(MainActivity.MSG, MainActivity.ACTION);
			
			PendingIntent pi = PendingIntent.getBroadcast(arg0, 0, intent, 0);
			
			Calendar setRingTime = Calendar.getInstance(Locale.KOREA);
			setRingTime.set(Calendar.HOUR_OF_DAY, 23);
			setRingTime.set(Calendar.MINUTE, 30);
			
			Calendar setVibrateTime = Calendar.getInstance(Locale.KOREA);
			setVibrateTime.set(Calendar.HOUR_OF_DAY, 10);
			setVibrateTime.set(Calendar.MINUTE, 0);
			
			AlarmManager am = (AlarmManager) arg0.getSystemService(Context.ALARM_SERVICE);
			am.setInexactRepeating(AlarmManager.RTC, setRingTime.getTimeInMillis(),
					1000 * 60 * 24, pi);
			am.setInexactRepeating(AlarmManager.RTC, setVibrateTime.getTimeInMillis(), 1000 * 60 * 24, pi);

			Log.d("Intent", "intent : start end");
			
		} else if(msg.equals(MainActivity.ACTION)) {
			AudioManager au = (AudioManager) arg0.getSystemService(Context.AUDIO_SERVICE);
			
			boolean vibrateStatus = isVibrateMode();
			
			if (vibrateStatus) {
				au.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			} else {
				int maxVol = au.getStreamMaxVolume(AudioManager.RINGER_MODE_NORMAL);
				
				au.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				au.setStreamVolume(AudioManager.RINGER_MODE_NORMAL, maxVol, AudioManager.STREAM_RING);
			}
		}
	}

	private boolean isVibrateMode() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		int hour = cal.get(Calendar.HOUR_OF_DAY);

		if (hour < baseHour) {
			return true;
		} else {
			return false;
		}
	}
}
