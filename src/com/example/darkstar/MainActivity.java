package com.example.darkstar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	
	GameScreen gameScreen;
	ScoreScreen scoreScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		
		//gameScreen=new GameScreen(this, this);
		//this.setContentView(gameScreen);
	}
	
	public void playGame(View view){
		gameScreen=new GameScreen(this, this);
		this.setContentView(gameScreen);
	}
	
	public void highScoreScreen(View view){
		scoreScreen=new ScoreScreen(this, this);
		this.setContentView(scoreScreen);
	}
	
	public void exitGame(View view){
		System.exit(0);
	}
}
