package com.mod.template;

import org.w3c.dom.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class StateActivity extends Activity {
	protected static String mSearchFilter = "";
	protected static String[] mActorFilter = new String[0];
	protected static int mSelectedContent = -1;
	protected static int mSelectedVideo = -1;
	protected static Document mConfig = null;
	
	protected static final int NO_SELECTION = -1;
	
	protected void displayErrorThenExit(String error){
		// Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fatal Error")
        .setMessage(error)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   StateActivity.this.finish();
                   }
               });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
	}
	
	public void onClickBack(View v){
		onBackPressed();
	}
}
