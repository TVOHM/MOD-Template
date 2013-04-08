package com.mod.template;

import java.util.HashSet;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("DefaultLocale")
public class ContentObject {
	private String mName = "";
	private String mText = "";
	private String[] mActors = new String[0];
	private VideoObject[] mVideos = new VideoObject[0];
	
	public ContentObject(Context context, Element element) throws ResourceNotFoundException {
		mName = element.getAttribute("name");
		mText = element.getAttribute("text");
		mActors = parseActors(element.getAttribute("actors"));
		mVideos = parseVideos(context, element.getElementsByTagName("video"));
	}
	
	public String getName() {
		return mName;
	}
	
	public String getText() {
		return mText;
	}
	
	public String[] getActors() {
		return mActors;
	}
	
	public VideoObject[] getVideos() {
		return mVideos;
	}
	
	@SuppressLint("DefaultLocale")
	public boolean containsActor(String[] search_actors){
		for(String search_actor : search_actors){
			for(String actor : mActors){
				if(actor.toLowerCase().contains(search_actor.toLowerCase()))
					return true;
			}
		}
		return false;
	}
	
	@SuppressLint("DefaultLocale")
	public boolean containsString(String string){
		if(mName.toLowerCase().contains(string.toLowerCase()) ||
				mText.toLowerCase().contains(string.toLowerCase()) ||
				containsActor(new String[] { string })) {
			return true;
		}
		
		for(VideoObject video : mVideos) {
			if(video.getName().toLowerCase().contains(string.toLowerCase())){
				return true;
			}
		}
		
		return false;
	}
	
	private String[] parseActors(String actors){
		HashSet<String> parsed_actors = new HashSet<String>();
		for(String actor : actors.split(" ")){
			parsed_actors.add(actor);
		}
		return parsed_actors.toArray(new String[parsed_actors.size()]);
	}
	
	private VideoObject[] parseVideos(Context context, NodeList videos) throws ResourceNotFoundException{
		VideoObject[] parsed_videos = new VideoObject[videos.getLength()];
		for(int i = 0; i < parsed_videos.length; i++){
			parsed_videos[i] = new VideoObject(context, (Element)videos.item(i));
		}
		return parsed_videos;
	}
}
