package com.mod.template;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton button = (ImageButton) findViewById(R.id.listImageButton);
		button.setEnabled(false);
		
	    final ListView listview = (ListView) findViewById(R.id.listView1);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	        "Android", "iPhone", "WindowsMobile" };

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        list.remove(item);
	        adapter.notifyDataSetChanged();
	      }
	    });
	}
	
	public void onClickList(View v){
	}
	
	public void onClickFilter(View v){
		  //mSelectedItems = new ArrayList();  // Where we track the selected items
		    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    // Set the dialog title
		    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
			        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
			        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
			        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
			        "Android", "iPhone", "WindowsMobile" };
		    
		    builder.setTitle("Test")
		    // Specify the list array, the items to be selected by default (null for none),
		    // and the listener through which to receive callbacks when items are selected
		           .setMultiChoiceItems(values, null,
		                      new DialogInterface.OnMultiChoiceClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int which,
		                       boolean isChecked) {
		            	   /*
		                   if (isChecked) {
		                       // If the user checked the item, add it to the selected items
		                       mSelectedItems.add(which);
		                   } else if (mSelectedItems.contains(which)) {
		                       // Else, if the item is already in the array, remove it 
		                       mSelectedItems.remove(Integer.valueOf(which));
		                   }
		                   */
		               }
		           })
		    // Set the action buttons
		           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   // User clicked OK, so save the mSelectedItems results somewhere
		                   // or return them to the component that opened the dialog
		               }
		           })
		           .setNegativeButton("NOT OK", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		               }
		           });

		    builder.create();
		    builder.show();
	}
	
	public void onClickSearch(View v){
		final EditText input = new EditText(this);
	    
		// Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search")
        .setMessage("Do I need to add this?")
        .setView(input)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               })
           	   .setNegativeButton("NOT OK", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });
        
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
	}
	
	public void onClickAbout(View v){
    
		// Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About")
        .setMessage("I love pie")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               });
        
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
	}
}
