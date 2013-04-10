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
		appendText(selected.getText(), android.R.style.TextAppearance_Small, Gravity.LEFT);
		
		appendVideo(null);
		appendVideo(null);
		appendVideo(null);
		appendVideo(null);
		appendVideo(null);
		appendVideo(null);
	}
	
	public void onClickBack(View v){
	    Intent intent = new Intent(ContentActivity.this, MainActivity.class);
	    ContentActivity.this.startActivity(intent);
	    ContentActivity.this.finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		onClickBack(null);
	}
	
	private void appendVideo(Element video){
		appendPadding(15);
		appendText("Meow Woof", android.R.style.TextAppearance_Medium, Gravity.CENTER);
		appendImage("night02i", "MyVideo");
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
	
	private void appendImage(String Image, final String Video){
		final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutScroll);
		ImageView image = new ImageView(this);
		int x = getResources().getIdentifier(Image, "raw", this.getPackageName());
		image.setImageResource(x);
		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		image.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(ContentActivity.this, VideoActivity.class);
			    ContentActivity.this.startActivity(intent);
			    ContentActivity.this.finish();
		    }
		});
		
		layout.addView(image);
	}
}
