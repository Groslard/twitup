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
		this.mDatabase.addObserver(this);
	}

	public void onUserLogged(String login, String password) {
		boolean loginOK = false;
		boolean passwordOK = false;
		this.mUsers = mDatabase.getUsers();

		for (User user : this.mUsers) {
			if (user.getName().equals(login)) {
				loginOK = true;
				if (user.getUserPassword().equals(password)) {
					this.mViewController.setConnectedUser(user);
					passwordOK = true;
					break;
				}
			}
		}

		if (!passwordOK) {
			this.mViewController.compConnexion.setErrorMessage("Password incorrect");
		}else{
			this.mViewController.compConnexion.setErrorMessage("");
		}
		if (!loginOK) {
			this.mViewController.compConnexion.setErrorMessage("Login incorrect");
		}else{
			this.mViewController.compConnexion.setErrorMessage("");
		}

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
			} else {
				// gerer affichage tag deja utilise
				this.mViewController.compInscription.setErrorMessage("Tag déja utilisé");
			}
		}
	}
	
	public void showUsers(){
		this.mUsers.remove(this.mViewController.getConnectedUser());
		mViewController.getCompUsersQueue().notifyUsersUpdated(this.mUsers);
	}
	
	
	public void loadUsersFollowed(){
		System.out.println("taille de la liste de "+mDatabase.getFollowed(mViewController.getConnectedUser()).size());
		mViewController.getCompUsersQueue().notifyUsersUpdated(mDatabase.getFollowed(mViewController.getConnectedUser()));
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
		if(addedUser != mViewController.getConnectedUser())
			this.mUsers.add(addedUser);
		
		if(this.mViewController.getConnectedUser() != null)
			this.showUsers();
		
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
