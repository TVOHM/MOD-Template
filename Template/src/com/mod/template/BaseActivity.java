package com.mod.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends Activity {
	protected static String mSearchFilter = "";
	protected static String[] mActorFilter = new String[0];
	protected static int mSelectedContent = -1;
	protected static String mSelectedVideo = "";
	protected static Document mConfig = null;
	
	/**
	 * Contains the contents of the application. May be filtered
	 * depending on user requirements.
	 */
	protected static ContentObject[] mContents = new ContentObject[0];
	
	protected void shutdown(){
		mContents = new ContentObject[0];
		mSearchFilter = "";
		mActorFilter = new String[0];
		mSelectedContent = -1;
		mSelectedVideo = "";
	}
	
	protected void displayErrorThenExit(String error){
		// Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fatal Error")
        .setMessage(error)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   BaseActivity.this.finish();
                   }
               });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
	}
	
	protected String toCSVList(String[] array){
		String ret = "";
		if(array.length > 0){
			for(String string : array){
				ret += string + ", ";
			}
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret;
	}
	
	private String[] getActors(ContentObject[] contents){
		// TODO: Update
		HashSet<String> actors = new HashSet<String>();
		for(ContentObject content : contents){
			for(String actor : content.getActors())
				actors.add(actor);
		}
		return (String[]) actors.toArray(new String[actors.size()]);
	}
	
	public void onClickFilter(View v){
		  //mSelectedItems = new ArrayList();  // Where we track the selected items
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    final String[] actors = getActors(mContents);
	    final boolean[] selected = new boolean[(actors.length)];
	    Arrays.fill(selected, false);
	    
	    builder.setTitle("Filter")
	    // Specify the list array, the items to be selected by default (null for none),
	    // and the listener through which to receive callbacks when items are selected
	           .setMultiChoiceItems(actors, null,
	                      new DialogInterface.OnMultiChoiceClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int which,
	                       boolean isChecked) {
	            	   selected[which] = isChecked;
	               }
	           })
	           // Set the action buttons
	           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   BaseActivity.this.mSearchFilter = "";
	            	   ArrayList<String> actorfilter = new ArrayList<String>();
	            	   for(int i = 0; i < actors.length; i++) {
	            		   if(selected[i])
	            			   actorfilter.add(actors[i]);
	            	   }
	            	   mActorFilter = actorfilter.toArray(new String[actorfilter.size()]);
	            	   BaseActivity.this.launchIntent(ListActivity.class);
	               }
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });
	    builder.show();
	}
	
	public boolean onClickSearch(View v){
		final EditText input = new EditText(this);
	    
		// Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search")
        //.setMessage("")
        .setView(input)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   BaseActivity.this.mSearchFilter = input.getText().toString();
                	   BaseActivity.this.mActorFilter = new String[0];
                	   BaseActivity.this.launchIntent(ListActivity.class);
                   }
               })
           	   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });
        builder.show();
        
        return true;
	}
	
	public void onClickBack(View v){
		onBackPressed();
	}
	
	@Override
	public boolean onSearchRequested() {
		return onClickSearch(null);
	}
	
	public void onClickHome(View v){
		BaseActivity.this.mSearchFilter = "";
		BaseActivity.this.mActorFilter = new String[0];
		BaseActivity.this.launchIntent(ListActivity.class);
	}
	
	protected void launchIntent(Class <?> cls){
	    Intent intent = new Intent(BaseActivity.this, cls);
	    BaseActivity.this.startActivity(intent);
	    BaseActivity.this.finish();
	}
}
