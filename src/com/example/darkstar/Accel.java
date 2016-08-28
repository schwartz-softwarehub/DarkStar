package com.example.darkstar;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accel implements SensorEventListener {
	
	SensorManager manager;
	Sensor accelerometer;
	Activity act;
	private int x;
	private float xVel, yVel;
	private static boolean accelDive;
	
	public Accel(Activity activity){
		this.act=activity;
		manager = (SensorManager) this.act.getSystemService(Context.SENSOR_SERVICE);
		accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		accelDive=false;
	}
	
	public static boolean accelDive(){
		return accelDive;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			yVel=event.values[1];
			
			if(event.values[1]>0){
			x++;
			accelDive=true;
			}
			if(event.values[1]<0){
				x--;
				accelDive=false;
			}
			
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	public float getYVel(){
		return yVel;
	}
	
	public int getX(){
		return this.x;
	}

}
