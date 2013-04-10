package com.mod.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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

public class MainActivity extends StateActivity {
	
	/**
	 * Contains the contents of the application. May be filtered
	 * depending on user requirements.
	 */
	private ContentObject[] mContents = new ContentObject[0];
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			mContents = parseContents(mConfig);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			displayErrorThenExit("Resource " + e.getMessage() + " not found");
			return;
		}
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
		
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, getNames(mContents));
	    
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
		    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
		    MainActivity.this.startActivity(intent);
		    MainActivity.this.finish();
	      }
	    });
	}
	
	private String[] getActors(ContentObject[] contents){
		HashSet<String> actors = new HashSet<String>();
		for(ContentObject content : contents){
			for(String actor : content.getActors())
				actors.add(actor);
		}
		return (String[]) actors.toArray(new String[actors.size()]);
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
	
	public void onClickList(View v){
	}
	
	public void onClickFilter(View v){
		  //mSelectedItems = new ArrayList();  // Where we track the selected items
		    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    
		    builder.setTitle("Filter")
		    // Specify the list array, the items to be selected by default (null for none),
		    // and the listener through which to receive callbacks when items are selected
		           .setMultiChoiceItems(getActors(mContents), null,
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
                	   MainActivity.this.mSearchFilter = input.getText().toString();
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
	
	public void onClickAbout(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About")
        .setMessage("TODO: Write About")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) { }
               });
        builder.create();
        builder.show();
	}

	@Override
	public boolean onSearchRequested() {
		return onClickSearch(null);
	}
	
	
}
