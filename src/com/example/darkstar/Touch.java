package com.example.darkstar;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Touch implements OnTouchListener {
	private float x;
	private float y;
	private boolean touch;
	public Touch(View v){
		v.setOnTouchListener(this);
		touch=false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		this.x=event.getX();
		this.y=event.getY();
		//System.exit(0);// TEST CODE
		///GameScreen.onTouch(v);
		touch=true;
		return false;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public boolean getTouch(){
		return this.touch;
	}
	
	public void setTouch(boolean t){
		this.touch=t;
	}

}
