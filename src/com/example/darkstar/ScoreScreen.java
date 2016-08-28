package com.example.darkstar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class ScoreScreen extends View{
	
	private int highScore;
	private String scoreString;
	private Touch touch;
	Paint p;
	private Activity scoreActivity;
	private Drawable background;

	public ScoreScreen(Context context, Activity act ) {
		super(context);
		highScore=0;
		scoreString="";
		changeScore(context);
		touch=new Touch(this);
		scoreActivity=act;
		background=context.getResources().getDrawable(R.drawable.bg1);
		
	}
	
	private void changeScore(Context context){
		scoreString=readFromFile(context);
		//highScore=Integer.parseInt(scoreString);
	}
	
	private void onTouch(){
		//System.exit(0);
		touch.setTouch(false);
		scoreActivity.setContentView(R.layout.activity_main);
	}
	
	private String readFromFile(Context context) {

	    String ret = "";

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
	
	private void update(){
		if(touch.getTouch()==true){
			onTouch();
		}
		
		invalidate();
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		p=new Paint();
		p.setTextSize(58);
		p.setColor(Color.RED);
		background.setBounds(canvas.getClipBounds());
		background.draw(canvas);
		//p.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
		//p.setFontFeatureSettings("afrc");
		
		canvas.drawText("HIGHSCORE: " +scoreString, getRight()/4, getHeight()/2, p);
		
		update();
	}

}
