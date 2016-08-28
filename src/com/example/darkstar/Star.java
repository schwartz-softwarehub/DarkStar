package com.example.darkstar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Star {
	private int canvasWidth, canvasHeight;
	private float x, y;
	private float xVel, yVel, frameTime, yS;
	private Bitmap star1;
	private Bitmap star1R;
	private int starWidth, starHeight;

	public Star(View view, int bx, int by) {
		x = 0;
		y = 0;
		yVel=0.0f;
		frameTime=0.0f;
		yS=0.0f;
		canvasWidth = bx;
		canvasHeight = by;
		resetCoor();
		starWidth=canvasWidth/4;
		starHeight=canvasHeight/4;
		star1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.darkshootingstar1);//WAS ORIGINALLLY shootingstar1
		star1R = Bitmap.createScaledBitmap(star1, starWidth, starHeight, false);
	}

	public void resetCoor() {
		x = 0;
		y = canvasHeight / 2;
	}

	public void update(float accelY) {
		frameTime= 0.666f;
		yVel=0.0f;
		yVel+=(accelY*frameTime);
		yS = (yVel / 2) * frameTime;
		if(y>0 && accelY<0){
			y-=10;
			
		}
		
		 if(y < canvasHeight - canvasHeight / 4 && accelY>0){
			y+=10;
			
		}
		/*if (Accel.accelDive() == false && y < canvasHeight - canvasHeight / 4) {
			//y += canvasHeight % 4;
			y+=yS;
		}

		else if (Accel.accelDive() == true && y > 0) {
			y -= canvasHeight % 4;
		}*/
	}
	
	public float getYVel(){
		return yVel;
	}
	
	public int getWidth(){
		return canvasWidth/4;
	}
	
	public int getHeight(){
		return canvasHeight/4;
	}
	
	public float getYS(){
		return yS;
	}

	public Bitmap getStar() {
		return star1R;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

}
