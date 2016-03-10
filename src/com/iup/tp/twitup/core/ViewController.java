package com.iup.tp.twitup.core;
import java.io.File;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.connexion.ConnexionComponent;
import com.iup.tp.twitup.ihm.connexion.InscriptionComponent;
import com.iup.tp.twitup.ihm.connexion.AccueilComponent;
import com.iup.tp.twitup.ihm.contents.TweetsComponent;
import com.iup.tp.twitup.ihm.contents.NewTweetComponent;
import com.iup.tp.twitup.ihm.contents.ProfilComponent;
import com.iup.tp.twitup.ihm.contents.SearchComponent;
import com.iup.tp.twitup.ihm.contents.UsersComponent;
import com.iup.tp.twitup.ihm.menu.MenuComponent;

public class ViewController {

	protected Twitup mTwitUp;
	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;
	
	/** CONTENT COMPONENTS **/
	protected MenuComponent compMenu;
	
	protected TweetsComponent compTweets;
	protected ProfilComponent compProfil;
	protected SearchComponent compSearch;
	protected UsersComponent compUsers;
	
	protected NewTweetComponent compNewTweet;
	
	/** Component for log in pan **/
	protected AccueilComponent compAccueil;
	protected ConnexionComponent compConnexion;
	protected InscriptionComponent compInscription;

	protected User connectedUser;
	
	public ViewController(Twitup mTwitUp) {
		super();
		this.mTwitUp = mTwitUp;
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView = new TwitupMainView(this);
		
		/** Instanciation des panels **/
		// connexion
		compAccueil = new AccueilComponent(this);
		compConnexion = new ConnexionComponent(this.mTwitUp.mUserController);
		compInscription = new InscriptionComponent(this.mTwitUp.mUserController);
		
		changeLogFormPanel(compConnexion);
		
		//menu
		compMenu = new MenuComponent(this);
		
		// panel principal / central
		compTweets = new TweetsComponent();
		compProfil = new ProfilComponent();
		compSearch = new SearchComponent();
		compUsers = new UsersComponent();
		compNewTweet = new NewTweetComponent();
		
		this.mMainView.showGUI();
		
		/** POSITIONNEMENT DES PREMIERS COMPOSANTS **/
		this.changeMainViewPanel(compAccueil);
		
		this.mTwitUp.addDatabaseObserver(this.mMainView);
	}
	
	/** MENU INTERACTION **/
	public void onMenuAccueilClicked(){
		changeMainViewPanel(compTweets);
	}
	
	public void onMenuUsersClicked(){
		changeMainViewPanel(compUsers);
	}
	
	public void onMenuProfilClicked(){
		changeMainViewPanel(compProfil);
	}
	
	public void onMenuNewTweetClicked(){
		changeMainViewPanel(compNewTweet);
	}
	
	public void onMenuRechercheClicked(){
		changeMainViewPanel(compSearch);
	}
	
	public void onMenuDisconnectClicked(){
		onUserDisconnected();
	}
	
	
	/** CONNEXION PAN SWITCH ACTION **/
	public void onLogConnexionClicked(){
		changeLogFormPanel(compConnexion);
	}
	
	public void onLogInscriptionClicked(){
		changeLogFormPanel(compInscription);
	}
	
	
	
	/** CONNECT/DISCONNECT ACTION **/
	public void onUserLogged(){
		changeMainViewPanel(compTweets);
		changeLeftPanel(compMenu);
	}
	
	public void onUserDisconnected(){
		this.connectedUser=null;
		changeMainViewPanel(compAccueil);
		changeLeftPanel(null);
	}
	
	
	/** PANELS UPDATES METHODS **/
	private void changeMainViewPanel(JPanel jpanel) {
		this.mMainView.setCenterPan(jpanel);
	}
	
	private void changeLeftPanel(JPanel jpanel) {
		this.mMainView.setLeftPan(jpanel);
	}
	
	private void changeLogFormPanel(JPanel jpanel){
		compAccueil.setFormPan(jpanel);
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
	 * @param pan
	 */
	public static void updatePan(JPanel pan){
		pan.revalidate();
		pan.repaint();
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}
	
}
