package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IUserlistObserver;

public class UserController implements IDatabaseObserver {

	protected ViewControllerJfx mViewController;
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
	
	protected SharedService shared;

	protected final Set<IUserlistObserver> mObservers = new HashSet<IUserlistObserver>();
	

	public UserController(ViewControllerJfx mViewController, EntityManager mEntityManager, IDatabase mDatabase, SharedService shared)  {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.mViewController = mViewController;
		this.mUsers = new HashSet<>();
		this.mDatabase.addObserver(this);
		this.shared = shared;
		
	}

	public void onUserLogged(String login, String password) {
		boolean loginOK = false;
		boolean passwordOK = false;
		this.mUsers = mDatabase.getUsers();

		for (User user : this.mUsers) {
			if (user.getName().equals(login)) {
				loginOK = true;
				if (user.getUserPassword().equals(password)) {
					this.mViewController.onUserLogged(user);
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
				this.mViewController.onUserLogged(userAdd);
			} else {
				// gerer affichage tag deja utilise
				this.mViewController.compInscription.setErrorMessage("Tag déja utilisé");
			}
		}
	}
	
	
	public void addObserver(IUserlistObserver observer) {
		this.mObservers.add(observer);
	}
	
	
	private void notifyObservers() {
		for (IUserlistObserver observer : mObservers) {
			observer.notifyUserListHasChanged(mUsers);
		}
	}
	
	
	public void showUsers(){
		this.mUsers.remove(shared.getConnectedUser());
		mViewController.getCompUsersQueue().notifyUserListHasChanged(this.mUsers);
	}
	
	
	public void loadUsersFollowed(){
		System.out.println("taille de la liste de "+mDatabase.getFollowed(shared.getConnectedUser()).size());
		mViewController.getCompUsersQueue().notifyUserListHasChanged(mDatabase.getFollowed(shared.getConnectedUser()));
	}
	
	public void followUser(User user){
		shared.getConnectedUser().addFollowing(user.getUserTag());
		mEntityManager.sendUser(shared.getConnectedUser());
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
		if(addedUser != shared.getConnectedUser())
			this.mUsers.add(addedUser);
		
		if(this.shared.getConnectedUser() != null)
			this.showUsers();
		
		notifyObservers();
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		mUsers.remove(deletedUser);
		this.notifyObservers();
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}