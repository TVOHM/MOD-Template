package com.mod.template;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		final VideoView video = (VideoView) findViewById(R.id.videoView1);
		Uri file = Uri.parse("android.resource://" + getPackageName() + "/" 
		        + this.getResources().getIdentifier("night02", "raw", this.getPackageName()));
		video.setVideoURI(file);
		MediaController mediaController = new MediaController(this);
		video.setMediaController(mediaController);
		video.setOnPreparedListener(new OnPreparedListener() {
		    @Override
		    public void onPrepared(MediaPlayer mp) {
		        mp.setLooping(true);
		    }
		});
		video.start();
	}
	
	public void onClickBack(View v){
		mSelectedVideo = NO_SELECTION;
	    Intent intent = new Intent(VideoActivity.this, ContentActivity.class);
	    VideoActivity.this.startActivity(intent);
	    VideoActivity.this.finish();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		onClickBack(null);
	}
	
}
