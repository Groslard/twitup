package com.iup.tp.twitup.ihm.contents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IUserlistObserver;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class UsersQueueComponentFx extends ScrollPane implements IUserlistObserver{

	
	protected Map<User, UsersComponentFx> userMap = new TreeMap<User, UsersComponentFx>(new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			return  (o2.getName().compareTo(o1.getName())  );
		}
	}); 
	
	protected GridPane contentPane= new GridPane();
	protected SearchUserComponent searchComponent = new SearchUserComponent();
	protected GridPane usersPane = new GridPane();
	protected UserController userController;
	
	public UsersQueueComponentFx(UserController userController){
		this.userController=userController;
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.contentPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		this.setContent(this.contentPane);
		this.contentPane.setVgap(5);
		this.contentPane.add(searchComponent, 0, 0);
		this.contentPane.add(usersPane, 0, 1);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	}
	@Override
	public synchronized void notifyUserListHasChanged(Set<User> users) {

		List<UsersComponentFx> newUsers = new ArrayList<UsersComponentFx>();
		for (User user : users) {

			UsersComponentFx component = userMap.get(user);

			// Nouveau user
			if (component == null) {
				UsersComponentFx newUserComponent = this
						.createUserComponent(user);
				this.addUserComponent(user, newUserComponent);
				newUsers.add(newUserComponent);
			}
		}

		List<UsersComponentFx> deletedUsers = new ArrayList<UsersComponentFx>();
		List<User> toRemove = new ArrayList<User>();
		for (User oldUser : userMap.keySet()) {
			if (users.contains(oldUser) == false) {
				UsersComponentFx oldUserComponent = userMap.get(oldUser);
				if (oldUserComponent != null) {
					deletedUsers.add(oldUserComponent);
				}
				toRemove.add(oldUser);
			}
		}
		for (User remove : toRemove) {
			userMap.remove(remove);
		}
		
		

		Runnable r = new Runnable() {

			@Override
			public void run() {
				updateUsersComponents(deletedUsers, newUsers);
			}
		};

		Thread t = new Thread(r);
		t.start();
	
		
	}
	


	protected void updateUsersComponents(
			
			List<UsersComponentFx> deletedUsers,
			List<UsersComponentFx> newUsers) {
			Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (UsersComponentFx oldUserComponent : deletedUsers) {
					oldUserComponent.hideUser();
				}

				replaceUser(new ArrayList<User>(userMap.keySet()));

				for (UsersComponentFx newUserComponent : newUsers) {
					newUserComponent.showUser();
				}
			}
		});
	}

	private void replaceUser(List<User> users) {

		int posY = 1;

		for (User user : users) {
			UsersComponentFx component = userMap.get(user);

			if (component != null) {
				GridPane.setConstraints(component, 0, posY);
				posY++;
			}
		}
	}

	protected void addUserComponent(User user, UsersComponentFx component) {
		userMap.put(user, component);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				contentPane.add(component, 0, 0);
				GridPane.setFillWidth(component, true);
				
				GridPane.setHalignment(component, HPos.CENTER);
			}
		});
	}

	protected UsersComponentFx createUserComponent(User user) {
		UsersComponentFx mockUserComponent = new UsersComponentFx(user,this.userController);
		mockUserComponent.setVisible(true);

		return mockUserComponent;
	}

	
	public SearchUserComponent getSearchComponent() {
		return searchComponent;
	}
	public Map<User, UsersComponentFx> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<User, UsersComponentFx> userMap) {
		this.userMap = userMap;
	}
		
	}
	

