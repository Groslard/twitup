package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.core.viewControllers.UsersQueuController;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class UserController implements IDatabaseObserver {

	protected UsersQueuController userQueu;
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

	protected SharedService sharedService;

	public UserController(UsersQueuController twitup, EntityManager mEntityManager, IDatabase mDatabase, SharedService sharedService)  {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.userQueu = twitup;
		this.mUsers = new HashSet<>();
		this.mDatabase.addObserver(this);
		this.sharedService = sharedService;
	}

	public void onUserLogged(String login, String password) {
		boolean loginOK = false;
		boolean passwordOK = false;
		this.mUsers = mDatabase.getUsers();

		for (User user : this.mUsers) {
			if (user.getName().equals(login)) {
				loginOK = true;
				if (user.getUserPassword().equals(password)) {
					this.sharedService.setConnectedUser(user);
					passwordOK = true;
					break;
				}
			}
		}

		if (!passwordOK) {
			this.userQueu.setConnexionErrorMessage("Password incorrect");
		}else{
			this.userQueu.setConnexionErrorMessage("");
		}
		if (!loginOK) {
			this.userQueu.setConnexionErrorMessage("Login incorrect");
		}else{
			this.userQueu.setConnexionErrorMessage("");
		}

	}

	public void onUserRegister(String login, String password, String tagEntry) {
		boolean isPresent = false;
		if (login.isEmpty()) {
			this.userQueu.setInscriptionErrorMessage("Veuillez remplir le login");
		} else if (password.isEmpty()) {
			this.userQueu.setInscriptionErrorMessage("Veuillez remplir le password");
		} else if (tagEntry.isEmpty()) {

			this.userQueu.setInscriptionErrorMessage("Veuillez remplir le tag");
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
				this.sharedService.setConnectedUser(userAdd);
				//this.userQueu.onUserLogged();
			} else {
				// gerer affichage tag deja utilise
				this.userQueu.setInscriptionErrorMessage("Tag déja utilisé");
			}
		}
	}
	
	public void showUsers(){
		this.mUsers.remove(this.sharedService.getConnectedUser());
		userQueu.notifyUsersUpdated(this.mUsers);
	}
	
	
	public void loadUsersFollowed(){
		System.out.println("taille de la liste de "+mDatabase.getFollowed(sharedService.getConnectedUser()).size());
		userQueu.notifyUsersUpdated(mDatabase.getFollowed(sharedService.getConnectedUser()));
	}
	
	public void followUser(User user){
		sharedService.getConnectedUser().addFollowing(user.getUserTag());
		mEntityManager.sendUser(sharedService.getConnectedUser());
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
		if(addedUser != sharedService.getConnectedUser())
			this.mUsers.add(addedUser);
		
		if(this.sharedService.getConnectedUser() != null)
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
