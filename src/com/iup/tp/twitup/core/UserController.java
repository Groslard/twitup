package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class UserController implements IDatabaseObserver {

	protected ViewController mViewController;
	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Liste des utilisateurs enregistrés.
	 */
	protected Set<User> mUsers;


	public UserController(ViewController mViewController, EntityManager mEntityManager, IDatabase mDatabase)  {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.mViewController = mViewController;
		this.mUsers = new HashSet<>();
	}

	public void onUserLogged(String login, String password) {
		boolean loginKO = true;
		boolean passwordKO = true;
		this.mUsers = mDatabase.getUsers();
		// parcours de sutilisaterurs

		// TODO code evitant de se logger
		this.mViewController.setConnectedUser(this.mUsers.iterator().next());
		this.mViewController.onUserLogged();
		return;
		//fin TODO
//		for (User user : this.mUsers) {
//			if (user.getName().equals(login)) {
//				if (user.getUserPassword().equals(password)) {
//					this.mViewController.setConnectedUser(user);
//					this.mViewController.onUserLogged();
//					passwordOK = false;
//				}
//				loginOK = false;
//
//			}
//		}
//
//		if (passwordOK) {
//			this.mViewController.compConnexion.setErrorMessage("Password incorrect");
//		}
//		if (loginOK) {
//			this.mViewController.compConnexion.setErrorMessage("Login incorrect");
//		}

	}

	public void onUserRegister(String login, String password, String tagEntry) {
		boolean isPresent = false;
		if (login.isEmpty()) {
			this.mViewController.compInscription.setErrorMessage("Veuillez remplir le login");
		} else if (password.isEmpty()) {
			this.mViewController.compInscription.setErrorMessage("Veuillez remplir le password");
		} else if (tagEntry.isEmpty()) {

			this.mViewController.compInscription.setErrorMessage("Veuillez remplir le tag");
		} else {

			this.mUsers = mDatabase.getUsers();
			// parcours de sutilisaterurs
			for (User user : this.mUsers) {
				if (user.getUserTag().equals(tagEntry)) {
					isPresent = true;

				}
			}
			if (!isPresent) {
				User userAdd = new User(UUID.randomUUID(), tagEntry, password, login, new HashSet<String>(), "");
				mEntityManager.sendUser(userAdd);
				this.mViewController.setConnectedUser(userAdd);
				this.mViewController.onUserLogged();
				this.loadUsers();
			} else {
				// gerer affichage tag deja utilise
				this.mViewController.compInscription.setErrorMessage("Tag déja utilisé");
			}

		}

	}
	
	public void loadUsers(){
		this.mUsers = mDatabase.getUsers();
		System.out.println(mViewController.getConnectedUser());
		this.mUsers.remove(mViewController.getConnectedUser());
		mViewController.getCompUsersQueue().showUsersList(this.mUsers);
	}
	
	
	public void loadUsersFollowed(){
		System.out.println("taille de la liste de "+mDatabase.getFollowed(mViewController.getConnectedUser()).size());
		mViewController.getCompUsersQueue().showUsersList(mDatabase.getFollowed(mViewController.getConnectedUser()));
	}
	
	public void followUser(User user){
		mViewController.getConnectedUser().addFollowing(user.getUserTag());
		mEntityManager.sendUser(mViewController.getConnectedUser());
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyUserAdded(User addedUser) {
		
		this.mUsers.add(addedUser);
		//this.sortTweets();
		mViewController.getCompUsersQueue().showUsersList(this.mUsers);
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}
