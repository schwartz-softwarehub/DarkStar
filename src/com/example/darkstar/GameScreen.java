package com.example.darkstar;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameScreen extends View  {
	
	Paint p;
	Accel accel;
	Activity act;
	Touch touch;
	private int x;
	private Star star;
	private boolean canvasStart;
	private Drawable bg;
	private Drawable deathbg;
	private boolean gameDeath;
	private long gameDeathTime;
	Activity gameActivity;
	Context gameContext;
	private int score;
	String testString;
	
	public GameScreen(Context context, Activity act){
		super(context);
		x=0;
		p=new Paint();
		accel=new Accel(act);
		touch=new Touch(this);
		canvasStart=false;
		bg=context.getResources().getDrawable(R.drawable.bg1);
		deathbg=context.getResources().getDrawable(R.drawable.deathbg);
		gameDeath=false;
		gameDeathTime=0;
		gameActivity=act;
		gameContext=context;
		score=0;
		testString="0";
	}
	
	public void update(){
		star.update(accel.getYVel());
		DarkEnergy.create(this);
		DarkEnergy.destroy();
		SpaceObject.create(this);
		SpaceObject.destroy();
		StarBeam.destroy();
		for(DarkEnergy d : DarkEnergy.darkEnergyList){
				d.update();
			}
		
		for(StarBeam s: StarBeam.starBeamList){
			s.update();
		}
		
		for(SpaceObject sp: SpaceObject.spaceObjectList){
			sp.update();
		}
		
		if(touch.getTouch()==true){
			onTouch(star.getX(), star.getY());
		}
		
		//COLLISION DETECTION
		for(StarBeam s:StarBeam.starBeamList){//FOR STARBEAM AND SPACEOBJECTS
			for(SpaceObject sp:SpaceObject.spaceObjectList){
				if(s.getX()>=sp.getX() && s.getX()<=(sp.getX()+star.getWidth())){
					if(s.getY()>=sp.getY() && s.getY()<=(sp.getY()+star.getHeight())){
						//System.exit(0);
						sp.setVisible(false);
						s.setVisible(false);
						score++;
					}
				}
			}
		}
		
		//COLLISION DETECTION FOR STAR AND DARK ENERGY
		for(DarkEnergy d: DarkEnergy.darkEnergyList){
			if(star.getX()+star.getWidth()>=d.getX() && star.getX()+star.getWidth()<=(d.getX()+d.getWidth())){
				if(star.getY()+star.getHeight()>=d.getY() && star.getY()+star.getHeight()<=(d.getY()+d.getHeight())){
					d.setVisible(false);
					gameDeath();
				}
					
			}
			
		}
		
		//COLLISION FOR STARBEAM AND DARK ENERGY
		
		for(StarBeam s:StarBeam.starBeamList){//FOR STARBEAM AND SPACEOBJECTS
			for(DarkEnergy sp:DarkEnergy.darkEnergyList){
				if(s.getX()>=sp.getX() && s.getX()<=(sp.getX()+star.getWidth())){
					if(s.getY()>=sp.getY() && s.getY()<=(sp.getY()+star.getHeight())){
						//System.exit(0);
						sp.setVisible(false);
						s.setVisible(false);
						gameDeath();
					}
				}
			}
		}
		
		this.invalidate();
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		if(canvasStart==false){
		setVarDim();
		star=new Star(this, getRight(), getHeight());
		DarkEnergy.setCanvasVars(getRight(), getHeight());
		SpaceObject.setCanvasVars(getRight(), getHeight());
		StarBeam.setCanvasVars(getRight(), getHeight());
		StarBeam.createBitmap(this);
		SpaceObject.createBitmap(this);
		//DarkEnergy.create();
		
		canvasStart=true;
		
		bg.setBounds(canvas.getClipBounds());
		deathbg.setBounds(canvas.getClipBounds());
		}
		
		if(gameDeath==true){
			deathbg.draw(canvas);
			gameDeath();
			//System.exit(0);
			//canvas.drawText(testString+ " "+ System.currentTimeMillis(), 10, 10, p);
		}
		
		else{
		
		//BACKGROUND DRAW
		bg.draw(canvas);
		
		//canvas.drawText(star.getYVel()+ " "+ accel.getYVel(), 10, 10, p);
		canvas.drawBitmap(star.getStar(), star.getX(), star.getY(), p);
		
		for(DarkEnergy d : DarkEnergy.darkEnergyList){
			canvas.drawBitmap(DarkEnergy.getBitmap(), d.getX(), d.getY(), p);
			}
		
		for(StarBeam s: StarBeam.starBeamList){
			canvas.drawBitmap(StarBeam.getBitmap(), s.getX(), s.getY(), p);
		}
		
		for(SpaceObject sp: SpaceObject.spaceObjectList){
			canvas.drawBitmap(SpaceObject.getBitmap(), sp.getX(), sp.getY(), p);
		}
		
		this.update();
		}//ELSE BRACKET FROM DEATHGAME IF STATEMENT
	}
	
	public void gameDeath(){
		if(gameDeath==false){
			gameDeathTime=System.currentTimeMillis();
			gameDeath=true;
			highScoreFile();
		}
		gameDeath=true;
		long num=System.currentTimeMillis();
		if(num>=gameDeathTime+5000){
			//System.exit(0);
			//this.invalidate();
			gameActivity.setContentView(R.layout.activity_main);
		}
		
		else {
			this.invalidate();
		}
	}
	
	public void setVarDim(){
	}
	
	public void onTouch(float f, float g){
		//StarBeam.create(this, f, g);
		StarBeam.create(star.getX()+star.getWidth(), star.getY()+star.getHeight()/2);
		touch.setTouch(false);
	}
	
	private void highScoreFile(){
		//File file = new File(gameContext.getFilesDir(), filename);
		//int fileScore=readFromFile(gameContext);
		//writeToFile(score+"", gameContext);
		 testString=readFromFile(gameContext);
		 //isInteger(testString);
		 if(isInteger(testString)==true){
		 int num=Integer.parseInt(testString);
		 if(score>num){
		 writeToFile(score+"", gameContext);
		 }
		 }
		//writeToFile(score, gameContext);

		
	}
	
	public boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	private void writeToFile(String data,Context context) {
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("score.txt", Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
	
	private String readFromFile(Context context) {

	    String ret = "0";

	    try {
	        InputStream inputStream = context.openFileInput("score.txt");

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	            System.out.println("written");
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	        System.out.println("ffff");
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
	
}