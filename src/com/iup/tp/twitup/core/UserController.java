package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class UserController {

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

	protected Twitup mTwitUp;

	public UserController(ViewController mViewController, EntityManager mEntityManager, IDatabase mDatabase) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.mViewController = mViewController;
		this.mUsers = new HashSet<>();
	}

	public void onUserLogged(String login, String password) {
		boolean loginOK = true;
		boolean passwordOK = true;
		this.mUsers = mDatabase.getUsers();
		// parcours de sutilisaterurs

		for (User user : this.mUsers) {
			if (user.getName().equals(login)) {
				if (user.getUserPassword().equals(password)) {
					this.mViewController.setConnectedUser(user);
					this.mViewController.onUserLogged();
					passwordOK = false;
				}
				loginOK = false;

			}
		}

		if (passwordOK) {
			this.mViewController.compConnexion.setErrorMessage("Password incorrect");
		}
		if (loginOK) {
			this.mViewController.compConnexion.setErrorMessage("Login incorrect");
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
				this.mDatabase.addUser(userAdd);
				mEntityManager.sendUser(userAdd);
				this.mViewController.setConnectedUser(userAdd);
				this.mViewController.onUserLogged();

			} else {
				// gerer affichage tag deja utilise
				this.mViewController.compInscription.setErrorMessage("Tag déja utilisé");
			}

		}

	}

}
