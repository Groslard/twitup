package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;


//class permettant de recuper l'utilisateur connecté sans passé la classe mère a tous les controllers
public class SharedService {
	private User connectedUser;

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}
	
	
}
