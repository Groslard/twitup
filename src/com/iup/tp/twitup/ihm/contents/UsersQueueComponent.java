package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.datamodel.User;

public class UsersQueueComponent extends JScrollPane {

	protected HashMap<User, UsersComponent> usersComponents; 

	protected JPanel content;

	protected GridBagConstraints userPlacement;

	private GridBagLayout gridBagLayout;
	
	protected UserController userController;
	
	public UsersQueueComponent(UserController userController) {
		this.userController=userController;
		this.usersComponents= new HashMap<User, UsersComponent>();
		this.content = new JPanel();
		this.content.setBackground(Color.WHITE);
		gridBagLayout = new GridBagLayout();
		this.content.setLayout(gridBagLayout);
		
		this.setViewportView(this.content);
		this.getVerticalScrollBar().setUnitIncrement(10);
		
		this.userPlacement = new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 70, 5, 70), 0, 0);
	}

	
	public void notifyUsersUpdated(Set<User> users){
//		Set<User> temp = usersComponents.keySet();
//		temp.removeAll(users);
//		for (User user : temp) {
//			UsersComponent usersComponent = this.usersComponents.get(user);
//			this.content.remove(usersComponent);
//			this.usersComponents.remove(user);
//		}
		
		int line = 0;
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			this.userPlacement.gridy = line;
			User user = iterator.next();
			
			UsersComponent userComp = this.usersComponents.get(user);
			
			if(userComp == null){
				userComp = new UsersComponent(user,this.userController);
				this.usersComponents.put(user, userComp);
				this.content.add(userComp, this.userPlacement);
			}else{
				System.out.println(user);
				// TODO : mettre a jour la constraint sur le comp si il existe
				gridBagLayout.setConstraints(userComp, this.userPlacement);
			}			
			line++;
		}
		ViewController.updatePan(content);
		
		
		
	}
	
}
