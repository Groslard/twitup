package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;

public class SharedService {
	private User connectedUser;

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}
	
	
}
