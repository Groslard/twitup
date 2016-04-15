package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IUserSearchObserver;
import com.iup.tp.twitup.ihm.IUserlistObserver;
import com.iup.tp.twitup.ihm.contents.UsersComponentFx;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class UserController implements IDatabaseObserver, IUserSearchObserver {

	protected ViewControllerJfx mViewController;
	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase database;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Liste des utilisateurs enregistrés.
	 */
	protected Set<User> mUsers;

	protected SharedService shared;

	protected String currentSearch = "";
	
	protected final Set<IUserlistObserver> mObservers = new HashSet<IUserlistObserver>();

	public UserController(ViewControllerJfx mViewController, EntityManager mEntityManager, IDatabase mDatabase,
			SharedService shared) {
		this.database = mDatabase;
		this.mEntityManager = mEntityManager;
		this.mViewController = mViewController;
		this.mUsers = new HashSet<>();
		this.database.addObserver(this);
		this.shared = shared;

	}

	public void onUserLogged(String login, String password) {
		boolean loginOK = false;
		boolean passwordOK = false;
		this.mUsers = database.getUsers();

		for (User user : this.mUsers) {
			if (user.getName().equals(login)) {
				loginOK = true;
				if (user.getUserPassword().equals(password)) {
					updateUserComponent(user);
					this.mViewController.onUserLogged(user);
					passwordOK = true;
					break;
				}
			}
		}

		if (!passwordOK) {
			this.mViewController.compConnexion.setErrorMessage("Password incorrect");
		} else {
			this.mViewController.compConnexion.setErrorMessage("");
		}
		if (!loginOK) {
			this.mViewController.compConnexion.setErrorMessage("Login incorrect");
		} else {
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

			this.mUsers = database.getUsers();
			// parcours de sutilisaterurs
			for (User user : this.mUsers) {
				if (user.getUserTag().equals(tagEntry)) {
					isPresent = true;

				}
			}
			if (!isPresent) {
				User userAdd = new User(UUID.randomUUID(), tagEntry, password, login, new HashSet<String>(), "");
				mEntityManager.sendUser(userAdd);
				updateUserComponent(userAdd);
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

	public void showUsers() {

		this.mUsers.remove(shared.getConnectedUser());
		mViewController.getCompUsersQueue().notifyUserListHasChanged(this.mUsers);
	}

	public void loadUsersFollowed() {
		mViewController.getCompUsersQueue().notifyUserListHasChanged(database.getFollowed(shared.getConnectedUser()));
	}

	public void followUser(User user, UsersComponentFx userComposant) {
		// cas unfollow
		if (userComposant.isFollowed()) {
			System.out
					.println("utilisateur :" + shared.getConnectedUser().getName() + "  a unfollow " + user.getName());
			shared.getConnectedUser().removeFollowing(user.getUserTag());
			mEntityManager.sendUser(shared.getConnectedUser());
			userComposant.setFollowed(false);
			userComposant.getBtnFollow().setText("Follow");
			this.notifyObservers();
		} else {// cas follow
			System.out.println("utilisateur :" + shared.getConnectedUser().getName() + "  a follow " + user.getName());
			shared.getConnectedUser().addFollowing(user.getUserTag());
			mEntityManager.sendUser(shared.getConnectedUser());
			userComposant.setFollowed(true);
			userComposant.getBtnFollow().setText("Unfollow");
			this.notifyObservers();
		}

	}

	
	//gestion des recherche
	public void searchUsers(String text) {


		currentSearch = text.toLowerCase();

		Set<User> newUserList = new HashSet<User>();

		// Récupération des twits à filtrer
		Set<User> databaseUsers = this.database.getUsers();

		for (User user : databaseUsers) {
			if(isSearched(user) && !newUserList.contains(user)){
				//cas user connected
				if(!user.getUserTag().equals(this.shared.getConnectedUser().getUserTag())){
					newUserList.add(user);
				}
				
			}
		}

		// Ajout de la nouvelle liste
		this.mUsers = newUserList;
		this.notifyObservers();
	}

	private boolean isSearched(User user) {
		User userConnected = this.shared.getConnectedUser();

		
				if ((user.getUserTag().toLowerCase().equals(currentSearch)
						|| user.getUserTag().toLowerCase().contains( currentSearch))) {
					return true;
				}
	
		return false;
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
		if (addedUser != shared.getConnectedUser())
			this.mUsers.add(addedUser);

		if (this.shared.getConnectedUser() != null)
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

	public SharedService getShared() {
		return shared;
	}

	public void setShared(SharedService shared) {
		this.shared = shared;
	}

	public void updateUserComponent(User user) {

		Map<User, UsersComponentFx> map = this.mViewController.compUsersQueue.getUserMap();
		Set<User> cles = map.keySet();
		Iterator<User> it = cles.iterator();
		while (it.hasNext()) {
			User cle = it.next();
			UsersComponentFx valeur = map.get(cle);

			// cas de k'utilisateur connecter
			if (cle.getUserTag().equals(user.getUserTag())) {
				GridPane parent=(GridPane) valeur.getParent();
				if(valeur!=null&&parent!=null&&parent.getChildren()!=null){
					parent.getChildren().remove(valeur);
				}
			
				
			}
			// cas utilisateur followed
			for (String tagFollowed : user.getFollows()) {
				if (tagFollowed.equals(cle.getUserTag())) {
					valeur.getBtnFollow().setText("Unfollow");
					valeur.setFollowed(true);
				} else {
					valeur.getBtnFollow().setText("Follow");
					valeur.setFollowed(false);
				}
			}
		}

	}

	public void updateUserProfil(String nom, String mdp,String pathImg){
		User userModif=this.shared.getConnectedUser();
		userModif.setAvatarPath(pathImg);
		userModif.setName(nom);
		userModif.setUserPassword(mdp);
		this.mEntityManager.sendUser(userModif);
	}
	@Override
	public void notifyUserSearched(String text) {
		this.searchUsers(text);
	}

}