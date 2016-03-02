package com.iup.tp.twitup.common;

import com.iup.tp.twitup.core.Twitup;

public class ConfigurationInterface {

	private Twitup twitup;
	
	
	
	public ConfigurationInterface(Twitup twitup) {
		super();
		this.twitup = twitup;
	}



	public void changeDirectory(String newPath){
		twitup.initDirectory(newPath);
	}
}
