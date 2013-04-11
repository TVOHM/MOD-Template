package com.mod.template;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {
	
	private static final int SPLASH_MIN_DURATION = 2250;

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
		
		try {
			mContents = parseContents(mConfig);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			displayErrorThenExit("Resource " + e.getMessage() + " not found");
			return;
		}
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
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
	
	/**
	 * 
	 * @param xDoc Document object representing the contents of config.xml
	 * @return Array that is populated with the contents defined in config.xml
	 */
	private ContentObject[] parseContents(Document xDoc) throws ResourceNotFoundException {		
		NodeList contents = xDoc.getDocumentElement().getElementsByTagName("signal");
		ContentObject[] parsed_contents = new ContentObject[contents.getLength()];
		for(int i = 0; i < parsed_contents.length; i++){
			parsed_contents[i] = new ContentObject(this, (Element)contents.item(i));
		}
		
		return parsed_contents;
	}	
}
