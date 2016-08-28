package com.example.darkstar;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public class DarkEnergy {
	
	private int x, y, width, height;
	private boolean visible;
	private static Bitmap dark1;
	private static Bitmap dark1Resized;
	private static boolean bitmapCreate=false;
	public static ArrayList<DarkEnergy> darkEnergyList=new ArrayList<DarkEnergy>();
	private static int canvasWidth, canvasHeight;
	private static int creationTimer=150;
	private Rect rect;
	
	public DarkEnergy(int ex, int ey){
		visible=true;
		x=ex;
		y=ey;
		rect=new Rect(0,0,0,0);
	}
	
	public void update(){
		//x-=10;//OG
		this.x-=canvasWidth/54;
		rect.set(x, y, canvasWidth/4, canvasHeight/8);
		
		if(x<0-canvasWidth / 4){
			visible=false;
		}
	}
	
	public static void create(View view){
		if(bitmapCreate==false){
			createBitmap(view);
			bitmapCreate=true;
		}
		if(darkEnergyList.size()<15 && creationTimer==0){
		DarkEnergy darkEnergy1=new DarkEnergy(canvasWidth+5, getRandomY());
		darkEnergyList.add(darkEnergy1);
		creationTimer=150;
		}
		
		else if(creationTimer>0){
			creationTimer--;
		}
		
		else if(creationTimer<=0 ){
			creationTimer=150;
		}
	}
	
	public static void destroy(){
		/*for(DarkEnergy d : darkEnergyList){
			if(d.getVisible()==false){
				darkEnergyList.remove(d);
			}
		}*/
		
		for(int i=0; i<darkEnergyList.size(); i++){
			DarkEnergy d=darkEnergyList.get(i);
			if(d.getVisible()==false){
				darkEnergyList.remove(i);
			}
		}
	}
	
	public static void setCanvasVars(int w, int h){
		canvasWidth=w;
		canvasHeight=h;
	}
	
	public static void createBitmap(View view){
		dark1=BitmapFactory.decodeResource(view.getResources(), R.drawable.dark1);
		dark1Resized = Bitmap.createScaledBitmap(dark1, canvasWidth / 4, canvasHeight / 8, false);
	}
	
	private static int getRandomY(){
		//int random=0+(int)(Math.random()*canvasHeight);
		Random rand = new Random();

	    int randomNum = rand.nextInt((canvasHeight - 2) + 1) + 2;

		
		return randomNum;
	}
	
	public static Bitmap getBitmap(){
		return dark1Resized;
	}
	
	public int getWidth(){
		return canvasWidth/4;
	}
	
	public int getHeight(){
		return canvasHeight/8;
	}
	
	public void setVisible(boolean v){
		this.visible=v;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
