package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.datamodel.User;

public class UsersQueueComponent extends JScrollPane {

	protected Set<UsersComponent> usersComponents;

	protected JPanel content;

	protected UserController userController;
	
	public UsersQueueComponent(UserController userController) {
		this.userController=userController;
		this.usersComponents= new HashSet<>();
		this.content = new JPanel();
		this.content.setBackground(Color.WHITE);
		this.content.setLayout(new GridBagLayout());
		
		
		this.setViewportView(this.content);
	}

	
	public void showUsersList(Set<User> users){
		this.content.removeAll();
		this.usersComponents.clear();
		
		int line = 0;
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()){
			UsersComponent userComp = new UsersComponent(iterator.next(),this.userController);
			this.usersComponents.add(userComp);
			
			
			this.content.add(userComp, new GridBagConstraints(0, line, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(0, 70, 5, 70), 0, 0));
			line++;
		}
		ViewController.updatePan(content);
	}
	
}
