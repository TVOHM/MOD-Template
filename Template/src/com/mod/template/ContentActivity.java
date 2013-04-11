package com.mod.template;

import org.w3c.dom.Element;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		ContentObject selected = mContents[mSelectedContent];
		appendText(selected.getName(), android.R.style.TextAppearance_Large, Gravity.CENTER);
		appendPadding(5);
		appendText(toCSVList(selected.getActors()), android.R.style.TextAppearance_Medium, Gravity.CENTER);
		appendPadding(15);
		appendText(selected.getText(), android.R.style.TextAppearance_Small, Gravity.CENTER);
		
		for(VideoObject video : selected.getVideos())
			appendVideo(video);
	}
	
	public void onClickBack(View v){
	    Intent intent = new Intent(ContentActivity.this, ListActivity.class);
	    ContentActivity.this.startActivity(intent);
	    ContentActivity.this.finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		onClickBack(null);
	}
	
	private void appendVideo(VideoObject video){
		appendPadding(15);
		appendText(video.getName(), android.R.style.TextAppearance_Medium, Gravity.CENTER);
		appendImage(video.getIconName(), video.getFileName());
	}
	
	private void appendPadding(int amount){
		final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutScroll);
		View padding = new View(this);
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, amount, getResources().getDisplayMetrics());
		padding.setLayoutParams(new LayoutParams(width, height));
		layout.addView(padding);
	}
	
	private void appendText(String text, int appearance, int gravity){
		final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutScroll);
		TextView t = new TextView(this);
		t.setTextAppearance(this, appearance);
		t.setText(text);
		t.setGravity(gravity);
		layout.addView(t);
	}
	
	/**
	 * Appends an image view to the bottom of the LinearList linearLayoutScroll in the video_activity view.
	 * When this image view is clicked the static variable mSelectedVideo is set to the Video parameter
	 * and the activity_video activity is launched.
	 * @param Image The string of the image resource for the icon
	 * @param Video The string of the video resource for this video
	 */
	private void appendImage(String Image, final String Video){
		// Get the required layout
		final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutScroll);
		
		// Create the image view
		ImageView image = new ImageView(this);
		int x = getResources().getIdentifier(Image, "raw", this.getPackageName());
		image.setImageResource(x);
		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		// Apply the listener
		image.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View v) {
		    	mSelectedVideo = Video;
			    Intent intent = new Intent(ContentActivity.this, VideoActivity.class);
			    ContentActivity.this.startActivity(intent);
			    ContentActivity.this.finish();
		    }
		});
		
		layout.addView(image);
	}
}
