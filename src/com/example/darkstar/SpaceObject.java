package com.example.darkstar;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public class SpaceObject {
	private int x, y;
	private boolean visible;
	private static boolean bitmapCreate=false;
	private static int canvasWidth, canvasHeight;
	private static int creationTimer=100;
	private Rect rect;
	 static Bitmap spaceObjectBitmap;
	 static Bitmap spaceObjectBitmapR;
	
	public static ArrayList<SpaceObject> spaceObjectList=new ArrayList<SpaceObject>();
	
	public SpaceObject(int ex, int ey){
		visible=true;
		x=ex;
		y=ey;
		rect=new Rect(0,0,0,0);
	}
	
	public void update(){
		//x-=10;//OG
		x-=canvasWidth/54;
		rect.set(x, y, canvasWidth/4,canvasWidth/4);
		
		if(x<0-canvasWidth / 4){
			visible=false;
		}
	}
	
	public static void create(View view){//CREATE METHOD
		if(bitmapCreate==false){
			//createBitmap(view);
			bitmapCreate=false;
		}
		
		if(spaceObjectList.size()<15 && creationTimer==0){
			//SpaceObject spaceObject=new SpaceObject(canvasWidth+5, getRandomY());
			SpaceObject spaceObject=new SpaceObject(canvasWidth+5, canvasHeight/2);
			spaceObjectList.add(spaceObject);
			creationTimer=150;
		}
		
		else if(creationTimer>0){
			creationTimer--;
		}
		
		else if(creationTimer<=0){
			creationTimer=100;
		}
	}
	
	public static void destroy(){
		for(int i=0; i<spaceObjectList.size(); i++){
			SpaceObject d=spaceObjectList.get(i);
			if(d.getVisible()==false){
				spaceObjectList.remove(i);
			}
		}
	}
	
	public boolean getVisible(){
		return this.visible;
	}
	
	public Rect getRect(){
		return this.rect;
	}
	
	public static void createBitmap(View view){
		spaceObjectBitmap=BitmapFactory.decodeResource(view.getResources(), R.drawable.shootingstar1);//WAS ORIGINALLLY shootingstar1
		spaceObjectBitmapR = Bitmap.createScaledBitmap(spaceObjectBitmap, canvasWidth/4, canvasHeight/4, false);
	}
	
	public static void setCanvasVars(int w, int h){
		canvasWidth=w;
		canvasHeight=h;
	}
	
	public static Bitmap  getBitmap(){
		return spaceObjectBitmapR;
	}
	
	private static int getRandomY(){
		Random rand=new Random();
		int randomNum=rand.nextInt((canvasHeight-2)+1)+2;
		return randomNum;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
