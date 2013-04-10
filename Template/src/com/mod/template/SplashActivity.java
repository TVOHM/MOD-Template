package com.mod.template;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends StateActivity {
	
	private static final int SPLASH_MIN_DURATION = 1750;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		try {
			mConfig = loadConfig();
		} catch (NotFoundException e) {
			e.printStackTrace();
			displayErrorThenExit("Could not find configuration file");
			return;
		} catch (SAXException e) {
			e.printStackTrace();
			displayErrorThenExit("Configuration file is invalid");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			displayErrorThenExit("Configuration IO failed");
			return;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			displayErrorThenExit("Configuration parser error");
			return;
		}
		
		if(mConfig.equals(null)) {
			displayErrorThenExit("Configuration object is null");
			return;
		}
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			    SplashActivity.this.startActivity(intent);
			    SplashActivity.this.finish();
			  }
			}, SPLASH_MIN_DURATION);
	}
	
	private Document loadConfig() 
			throws NotFoundException, SAXException, IOException, ParserConfigurationException {
		int XMLID = this.getResources().getIdentifier("config", "raw", this.getPackageName());
		Document xDoc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		xDoc = dbf.newDocumentBuilder().parse(getResources().openRawResource(XMLID));
		return xDoc;
	}
}
