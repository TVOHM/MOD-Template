package com.mod.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	
	private static final int SPLASH_DURATION = 1750;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			    SplashActivity.this.startActivity(intent);
			    SplashActivity.this.finish();
			  }
			}, SPLASH_DURATION);
	}
}
