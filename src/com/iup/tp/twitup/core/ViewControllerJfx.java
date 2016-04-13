package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMainViewFx;
import com.iup.tp.twitup.ihm.connexion.ConnexionComponent;
import com.iup.tp.twitup.ihm.connexion.ConnexionComponentFx;
import com.iup.tp.twitup.ihm.connexion.InscriptionComponent;
import com.iup.tp.twitup.ihm.connexion.InscriptionComponentFx;
import com.iup.tp.twitup.ihm.connexion.AccueilComponent;
import com.iup.tp.twitup.ihm.connexion.AccueilComponentFx;
import com.iup.tp.twitup.ihm.contents.TweetsQueueComponent;
import com.iup.tp.twitup.ihm.contents.TweetsQueueComponentFx;
import com.iup.tp.twitup.ihm.contents.NewTweetComponent;
import com.iup.tp.twitup.ihm.contents.ProfilComponent;
import com.iup.tp.twitup.ihm.contents.SearchComponent;
import com.iup.tp.twitup.ihm.contents.UsersQueueComponent;
import com.iup.tp.twitup.ihm.menu.MenuComponent;
import com.iup.tp.twitup.ihm.menu.MenuComponentFx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ViewControllerJfx {

	protected Twitup mTwitUp;
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

	protected NewTweetComponent compNewTweet;
	protected Boolean newTweetIsShow;

	/** Component for log in pan **/
	protected AccueilComponentFx compAccueil;
	protected ConnexionComponentFx compConnexion;
	protected InscriptionComponentFx compInscription;

	protected User connectedUser;
	private JFXPanel compTweetsContainer;

	public ViewControllerJfx(Twitup mTwitUp) {
		super();
		this.mTwitUp = mTwitUp;
		this.newTweetIsShow = false;
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

	public NewTweetComponent getCompNewTweet() {
		return compNewTweet;
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView = new TwitupMainViewFx(this);

		/** Instanciation des panels **/
		// connexion
		compAccueil = new AccueilComponentFx(this);
		compConnexion = new ConnexionComponentFx(this.mTwitUp.mUserController);
		compInscription = new InscriptionComponentFx(this.mTwitUp.mUserController);

		changeLogFormPanel(compConnexion);

		// menu
		compMenu = new MenuComponentFx(this);

		// panel principal / central
		compTweetsContainer = new JFXPanel();
		compTweetsQueue = new TweetsQueueComponentFx();
		initAndShowGUI(compTweetsContainer, compTweetsQueue);

		compProfil = new ProfilComponent();
		compSearch = new SearchComponent();
		compUsersQueue = new UsersQueueComponent(this.mTwitUp.mUserController);
		compNewTweet = new NewTweetComponent(this.connectedUser,
				this.mTwitUp.mTweetController);

		this.mMainView.showGUI();

		/** POSITIONNEMENT DES PREMIERS COMPOSANTS **/
		this.changeMainViewPanel(compAccueil);

		this.mTwitUp.addDatabaseObserver(this.mMainView);
	}

	protected static void initAndShowGUI(JFXPanel fxPanel,
			TweetsQueueComponentFx tweetQueue) {
		// JavaFX thread
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Scene scene = new Scene(tweetQueue, 350, 250);
				fxPanel.setScene(scene);
			}
		});
	}

	/** MENU INTERACTION **/
	public void onMenuAccueilClicked() {
		changeMainViewPanel(compTweetsContainer);
	}

	public void onMenuUsersClicked() {
		changeMainViewPanel(compUsersQueue);
	}

	public void onMenuProfilClicked() {
		changeMainViewPanel(compProfil);
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

	public void onMenuRechercheClicked() {
		changeMainViewPanel(compSearch);
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
		this.mTwitUp.mUserController.showUsers();
		this.mTwitUp.mTweetController.showTweets();
		changeMainViewPanel(compTweetsContainer);
		changeLeftPanel(compMenu);
	}

	public void onUserDisconnected() {
		this.connectedUser = null;
		changeMainViewPanel(compAccueil);
		changeLeftPanel(null);
	}

	/** PANELS UPDATES METHODS **/
	private void changeMainViewPanel(JComponent component) {
		this.mMainView.setCenterPan(component);
	}
	private void changeMainViewPanel(GridPane component) {
		this.mMainView.setCenterPan(component);
	}
	
	private void changeLeftPanel(JPanel jpanel) {
		this.mMainView.setLeftPan(jpanel);
	}

	private void changeRightPanel(JPanel jpanel) {
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

	/**
	 * Static method used to refresh pan on application
	 * 
	 * @param pan
	 */
	public static void updatePan(JPanel pan) {
		pan.revalidate();
		pan.repaint();
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
		this.onUserLogged();
	}

}
