package com.mod.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends BaseActivity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		populateList();
	}
	
	private String[] getNames(ContentObject[] contents){
		ArrayList<String> names = new ArrayList<String>();
		
		for(ContentObject content : contents){
			names.add(content.getName());
		}
		
		return names.toArray(new String[names.size()]);
	}
	
	private void populateList(){
		final ListView listview = (ListView) findViewById(R.id.listView1);
		
		ArrayList<ContentObject> contents = new ArrayList<ContentObject>();
		final List<Integer> relativeindexes = new ArrayList<Integer>();
		
		for(int i = 0; i < mContents.length; i++){
			// If there are filter actors, filter by these
			if(mActorFilter.length > 0 && mSearchFilter.length() == 0 && mContents[i].containsActor(mActorFilter)){
				contents.add(mContents[i]);
				relativeindexes.add(i);
			}
			// If there is a filter string, filter by string
			else if(mSearchFilter.length() > 0 && mActorFilter.length == 0 && mContents[i].containsString(mSearchFilter)){
				contents.add(mContents[i]);
				relativeindexes.add(i);
			}
			// Else just add everything
			else if (mActorFilter.length == 0 && mSearchFilter.length() == 0){
				contents.add(mContents[i]);
				relativeindexes.add(i);
			}
		}
		
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, getNames(contents.toArray(new ContentObject[contents.size()])));
	    
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	    	mSelectedContent = relativeindexes.get(position);
		    Intent intent = new Intent(ListActivity.this, ContentActivity.class);
		    ListActivity.this.startActivity(intent);
		    ListActivity.this.finish();
	      }
	    });
	}
	
	/*
	private void printFilter(){
		if(mSearchFilter.length() > 0 && mActorFilter.length == 0)
			Toast.makeText(this, "Showing search results for \"" + mSearchFilter + "\"", Toast.LENGTH_SHORT).show();
		else if (mActorFilter.length > 0 && mSearchFilter.length() == 0){
			String actors = "";
			for(String actor : mActorFilter)
				actors += actor + ", ";
			actors = actors.substring(0, actors.length() - 2);
			Toast.makeText(this, "Showing results performed by " + actors, Toast.LENGTH_SHORT).show();
		}
	}
	*/
	
	@Override
	public void onBackPressed(){
	       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("Exit")
	        .setMessage("Are you sure you want to quit?")
	               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   shutdown();
	                	   ListActivity.this.finish();
	                   }
	               })
	               .setNegativeButton("No", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                   }
	               });
	        // Create the AlertDialog object and return it
	        builder.create();
	        builder.show();
	}
}
