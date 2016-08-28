package com.example.darkstar;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public class StarBeam {
	
	private int x, y;
	private static int width;
	private static int height;
	private static int canvasWidth, canvasHeight;
	private boolean visible;
	private static Bitmap starBeam1;
	private static Bitmap starBeamResized;
	private Rect rect;
	
	public static ArrayList<StarBeam> starBeamList=new ArrayList<StarBeam>();

	public StarBeam(float f, float g){
		visible = true;
		this.x=(int)f;
		this.y=(int)g;
		//width=canvasWidth/6;
		//height=canvasWidth/6;
		
		rect=new Rect(0,0,0,0);
		
		//starBeam1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.shootingstar1);
		//starBeamResized = Bitmap.createScaledBitmap(starBeam1, width, height, false);
	}
	
	public void update(){
		//this.x+=30;//OG
		this.x+=canvasWidth/54;
		rect.set(x, y, canvasWidth/4, canvasWidth/4);//CHANGE DIM
	}
	
	public static void create( float f, float g){
		StarBeam starBeam=new StarBeam(f, g);
		starBeamList.add(starBeam);
	}
	
	public static void destroy(){
		for(int i=0; i<starBeamList.size();i++){
			StarBeam starBeam=starBeamList.get(i);
			if(starBeam.getVisible()==false){
				starBeamList.remove(i);
			}
		}
	}
	
	public static void setCanvasVars(int bx, int by){
		canvasWidth=bx;
		canvasHeight=by;
	}
	
	public static void createBitmap(View view){
		starBeam1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.projectile1);
		starBeamResized = Bitmap.createScaledBitmap(starBeam1, canvasWidth/6, canvasHeight/54, false);
	}
	
	public static Bitmap getBitmap(){
		return starBeamResized;
	}
	
	public Rect getRect(){
		return this.rect;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void setVisible(boolean v){
		this.visible=v;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
