package com.iup.tp.twitup.core;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.connexion.ConnexionComponent;
import com.iup.tp.twitup.ihm.connexion.InscriptionComponent;
import com.iup.tp.twitup.ihm.connexion.AccueilComponent;
import com.iup.tp.twitup.ihm.contents.TweetsQueueComponent;
import com.iup.tp.twitup.ihm.contents.NewTweetComponent;
import com.iup.tp.twitup.ihm.contents.ProfilComponent;
import com.iup.tp.twitup.ihm.contents.SearchComponent;
import com.iup.tp.twitup.ihm.contents.UsersQueueComponent;
import com.iup.tp.twitup.ihm.menu.MenuComponent;

import javafx.scene.layout.GridPane;

public class ViewController {

	protected Twitup mTwitUp;
	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;
	
	/** CONTENT COMPONENTS **/
	protected MenuComponent compMenu;
	
	protected TweetsQueueComponent compTweetsQueue;
	protected ProfilComponent compProfil;
	protected SearchComponent compSearch;
	protected UsersQueueComponent compUsersQueue;
	
	protected NewTweetComponent compNewTweet;
	protected Boolean newTweetIsShow;
	
	/** Component for log in pan **/
	protected AccueilComponent compAccueil;
	protected ConnexionComponent compConnexion;
	protected InscriptionComponent compInscription;

	protected User connectedUser;
	
	public ViewController(Twitup mTwitUp) {
		super();
		this.mTwitUp = mTwitUp;
		this.newTweetIsShow = false;
	}

	
	public UsersQueueComponent getCompUsersQueue() {
		return compUsersQueue;
	}


	public TweetsQueueComponent getCompTweetsQueue() {
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
		compTweetsQueue = new TweetsQueueComponent();
		compProfil = new ProfilComponent();
		compSearch = new SearchComponent();
		compUsersQueue = new UsersQueueComponent(this.mTwitUp.mUserController);
		compNewTweet = new NewTweetComponent(this.connectedUser, this.mTwitUp.mTweetController);
		
		this.mMainView.showGUI();
		
		/** POSITIONNEMENT DES PREMIERS COMPOSANTS **/
		this.changeMainViewPanel(compAccueil);
		
		this.mTwitUp.addDatabaseObserver(this.mMainView);
	}
	
	/** MENU INTERACTION **/
	public void onMenuAccueilClicked(){
		changeMainViewPanel(compTweetsQueue);
	}
	
	public void onMenuUsersClicked(){
		changeMainViewPanel(compUsersQueue);
	}
	
	public void onMenuProfilClicked(){
		changeMainViewPanel(compProfil);
	}
	
	public void onMenuNewTweetClicked(){
		if(newTweetIsShow){
			changeRightPanel(null);
			newTweetIsShow = false;
		}else{
			changeRightPanel(compNewTweet);
			newTweetIsShow = true;
		}
			
		
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
		this.mTwitUp.mUserController.showUsers();
		this.mTwitUp.mTweetController.showTweets();
		changeMainViewPanel(compTweetsQueue);
		changeLeftPanel(compMenu);
	}
	
	public void onUserDisconnected(){
		this.connectedUser=null;
		changeMainViewPanel(compAccueil);
		changeLeftPanel(null);
	}
	
	
	/** PANELS UPDATES METHODS **/
	private void changeMainViewPanel(JComponent component) {
		this.mMainView.setCenterPan(component);
	}
	
	private void changeLeftPanel(JPanel jpanel) {
		this.mMainView.setLeftPan(jpanel);
	}
	
	private void changeRightPanel(JPanel jpanel) {
		this.mMainView.setRightPan(jpanel);
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
		System.out.println(this.mMainView);
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
	public static void updatePan(GridPane pan){
	
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
		this.onUserLogged();
	}
	
}
