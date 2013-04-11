package com.mod.template;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Activity class that is used for playing videos.
 * Extends BaseActivity for access to the static variable mSelectedVideo.
 * @author Ian Edwards
 */
public class VideoActivity extends BaseActivity {
	
	/**
	 * The video view that is used to play the video data
	 */
	private VideoView mVideoView = null;
	/**
	 * Media controller that is used to provide video controls such as pause
	 */
	private MediaController mMediaController = null;
	
	/**
	 * Called when the activity is created. This also happens when the device is rotated.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Assert that the video file string contains data
		if(selectedVideoIsValid()){
			setContentView(R.layout.activity_video);
			
			// Get the view to play the video in
			mVideoView = (VideoView) findViewById(R.id.videoActivityVideoView);
			// Get the URI of the video file specified by mSelectedVideo
			Uri file = Uri.parse("android.resource://" + getPackageName() + "/" 
			        + this.getResources().getIdentifier(mSelectedVideo, "raw", this.getPackageName()));
			// Load the video into the video view
			mVideoView.setVideoURI(file);
			// Associate the video view with the media controller
			mMediaController = new MediaController(this);
			mVideoView.setMediaController(mMediaController);
			// Set the video to loop (this can be disabled if this behaviour is not desired)
			setVideoToLoop();
			// Start video
			mVideoView.start();
		}
		// If there is no data (error?), return to the content activity
		else
			onBackPressed();
	}
	
	/**
	 * Asserts that the static variable mSelectedVideo contains data
	 * @return False if mSelectedVideo is empty
	 */
	private boolean selectedVideoIsValid(){
		if(mSelectedVideo.length() == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Sets the video view to loop the video - if this behaviour is not desired simply do not call this method
	 */
	private void setVideoToLoop(){
		mVideoView.setOnPreparedListener(new OnPreparedListener() {
		    @Override
		    public void onPrepared(MediaPlayer mp) {
		        mp.setLooping(true);
		    }
		});
	}
	
	/**
	 * If the hard back button is pressed, create an intent to return to the content activity
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	    Intent intent = new Intent(VideoActivity.this, ContentActivity.class);
	    VideoActivity.this.startActivity(intent);
	    VideoActivity.this.finish();
	}
	
}
