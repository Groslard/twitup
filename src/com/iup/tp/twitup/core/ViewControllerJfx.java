package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupMainViewFx;
import com.iup.tp.twitup.ihm.connexion.ConnexionComponentFx;
import com.iup.tp.twitup.ihm.connexion.InscriptionComponentFx;
import com.iup.tp.twitup.ihm.connexion.SwitchConnexionInscriptionComponentFx;
import com.iup.tp.twitup.ihm.connexion.AccueilComponentFx;
import com.iup.tp.twitup.ihm.contents.TweetsQueueComponentFx;
import com.iup.tp.twitup.ihm.contents.NewTweetComponentFx;
import com.iup.tp.twitup.ihm.contents.ProfilComponent;
import com.iup.tp.twitup.ihm.contents.SearchComponent;
import com.iup.tp.twitup.ihm.contents.UsersQueueComponent;
import com.iup.tp.twitup.ihm.menu.MenuComponentFx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewControllerJfx {

	protected TwitupFx mTwitUp;
	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainViewFx mMainView;

	/** CONTENT COMPONENTS **/
	protected MenuComponentFx compMenu;

	protected TweetsQueueComponentFx compTweetsQueue;
	protected ProfilComponent compProfil;
	protected SearchComponent compSearch;
	protected UsersQueueComponent compUsersQueue;

	protected NewTweetComponentFx compNewTweet;
	protected Boolean newTweetIsShow;

	/** Component for log in pan **/
	protected AccueilComponentFx compAccueil;
	protected ConnexionComponentFx compConnexion;
	protected InscriptionComponentFx compInscription;
	protected SwitchConnexionInscriptionComponentFx switchCompAccueil;

	protected User connectedUser;

	protected Stage stage;

	public ViewControllerJfx(TwitupFx mTwitUp) {
		super();
		this.mTwitUp = mTwitUp;
		this.newTweetIsShow = false;
		this.stage = this.mTwitUp.getStage();
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {

		this.mMainView = new TwitupMainViewFx(this);

		/** Instanciation des panels **/
		// connexion
		this.compConnexion = new ConnexionComponentFx(this.mTwitUp.mUserController);
		this.compInscription = new InscriptionComponentFx(this.mTwitUp.mUserController);
		this.switchCompAccueil = new SwitchConnexionInscriptionComponentFx(this);
		this.compAccueil = new AccueilComponentFx(this);

		// changeLogFormPanel(compConnexion);

		// menu
		this.compMenu = new MenuComponentFx(this);

		// panel principal / central
		this.compTweetsQueue = new TweetsQueueComponentFx();

		// initAndShowGUI(this.stage, compTweetsQueue);

		this.compProfil = new ProfilComponent();
		this.compSearch = new SearchComponent();
		this.compUsersQueue = new UsersQueueComponent(this.mTwitUp.mUserController);
		this.compNewTweet = new NewTweetComponentFx(this.connectedUser, this.mTwitUp.mTweetController);

		this.mMainView.showGUI();

		/** POSITIONNEMENT DES PREMIERS COMPOSANTS **/
		this.changeMainViewPanel(this.compAccueil);
		this.compAccueil.setFormPan(this.compConnexion);
		this.compAccueil.setSwitchConnexion(this.switchCompAccueil);
		this.mTwitUp.addDatabaseObserver(this.mMainView);
	}

	protected static void initAndShowGUI(Stage stage, TweetsQueueComponentFx tweetQueue) {
		// JavaFX thread
		Scene scene = new Scene(tweetQueue, 350, 250);
		stage.setScene(scene);
	}

	/** MENU INTERACTION **/
	public void onMenuAccueilClicked() {
		changeMainViewPanel(compTweetsQueue);
	}

	public void onMenuUsersClicked() {
		changeMainViewPanel(compUsersQueue);
	}

	// a faire composant profil en jx
	public void onMenuProfilClicked() {
		// changeMainViewPanel(compProfil);
	}

	public void onMenuNewTweetClicked() {
		if (newTweetIsShow) {
			changeRightPanel(null);
			newTweetIsShow = false;
		} else {
			changeRightPanel(compNewTweet);
			newTweetIsShow = true;
		}

	}

	// a faire composant x pour la recherceh
	public void onMenuRechercheClicked() {
		// changeMainViewPanel(compSearch);
	}

	public void onMenuDisconnectClicked() {
		onUserDisconnected();
	}

	/** CONNEXION PAN SWITCH ACTION **/
	public void onLogConnexionClicked() {
		changeLogFormPanel(compConnexion);
	}

	public void onLogInscriptionClicked() {
		changeLogFormPanel(compInscription);
	}

	/** CONNECT/DISCONNECT ACTION **/
	public void onUserLogged() {
		changeMainViewPanel(compTweetsQueue);
		changeLeftPanel(compMenu);
	}

	public void onUserDisconnected() {
		this.connectedUser = null;
		changeMainViewPanel(compAccueil);
		changeLeftPanel(null);
	}

	/** PANELS UPDATES METHODS **/


	private void changeMainViewPanel(Node component) {
		this.mMainView.setCenterPan(component);
	}

	private void changeLeftPanel(Node component) {
		this.mMainView.setLeftPan(component);
	}

	private void changeRightPanel(Node jpanel) {
		this.mMainView.setRightPan(jpanel);
	}

	private void changeLogFormPanel(GridPane gridpane) {
		compAccueil.setFormPan(gridpane);
	}

	/** EXCHANGE DIRECTORY INTERACTION **/
	public void updateExchangeDirectory() {
		String newPath = this.getDirPath();
		mTwitUp.initDirectory(newPath);
	}

	public String getDirPath() {
		File filePath = this.mMainView.showGuiDirPath();
		if (filePath != null) {
			
			// On recupere le path selectionné
			return filePath.getAbsolutePath();
		} else {
			return null;
		}
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
		this.onUserLogged();
	}

	public UsersQueueComponent getCompUsersQueue() {
		return compUsersQueue;
	}

	public TweetsQueueComponentFx getCompTweetsQueue() {
		return compTweetsQueue;
	}

	public SearchComponent getCompSearch() {
		return compSearch;
	}

	public NewTweetComponentFx getCompNewTweet() {
		return compNewTweet;
	}

	public Stage getStage() {
		return stage;
	}

	public AccueilComponentFx getCompAccueil() {
		return compAccueil;
	}

	public void setCompAccueil(AccueilComponentFx compAccueil) {
		this.compAccueil = compAccueil;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ConnexionComponentFx getCompConnexion() {
		return compConnexion;
	}

	public void setCompConnexion(ConnexionComponentFx compConnexion) {
		this.compConnexion = compConnexion;
	}

	public InscriptionComponentFx getCompInscription() {
		return compInscription;
	}

	public void setCompInscription(InscriptionComponentFx compInscription) {
		this.compInscription = compInscription;
	}

	public SwitchConnexionInscriptionComponentFx getSwitchCompAccueil() {
		return switchCompAccueil;
	}

	public void setSwitchCompAccueil(SwitchConnexionInscriptionComponentFx switchCompAccueil) {
		this.switchCompAccueil = switchCompAccueil;
	}

}
