package com.mod.template;

public class ResourceNotFoundException extends Exception {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resource){
		super(resource);
	}

}
