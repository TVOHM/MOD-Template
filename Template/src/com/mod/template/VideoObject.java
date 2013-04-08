package com.mod.template;

import org.w3c.dom.Element;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("DefaultLocale")
public class VideoObject {
	private String mName = "";
	private String mFileName = "";
	private String mIconName = "";
	
	public VideoObject(Context context, Element element) throws ResourceNotFoundException{
		mName = element.getAttribute("name");
		
		if(parseVideoFile(context, element) == false)
			throw new ResourceNotFoundException(mFileName);
		
		if(parseIconFile(context, element) == false)
			throw new ResourceNotFoundException(mIconName);
	}
	
	public String getName() {
		return mName;
	}
	
	public String getFileName(){
		return mFileName;
	}
	
	public String getIconName() {
		return mIconName;
	}
	
	public boolean containsString(String string) {
		if(mName.toLowerCase().contains(string.toLowerCase()))
			return true;
		else
			return false;
	}
	
	private boolean parseVideoFile(Context context, Element element){
		mFileName = element.getAttribute("file_name");
		if(mFileName.length() > 0 && 
				context.getResources().getIdentifier(mFileName.substring(0, mFileName.lastIndexOf('.')), "raw", context.getPackageName()) == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean parseIconFile(Context context, Element element){
		mIconName = element.getAttribute("icon_name");
		if(mIconName.length() > 0 && 
				context.getResources().getIdentifier(mIconName.substring(0, mIconName.lastIndexOf('.')), "raw", context.getPackageName()) == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
