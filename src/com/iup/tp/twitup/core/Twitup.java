package com.iup.tp.twitup.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.viewControllers.LogController;
import com.iup.tp.twitup.core.viewControllers.MainController;
import com.iup.tp.twitup.core.viewControllers.NewTweetController;
import com.iup.tp.twitup.core.viewControllers.TweetsQueuController;
import com.iup.tp.twitup.core.viewControllers.UsersQueuController;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.mock.MockController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	protected TweetController mTweetController;

	protected UserController mUserController;
	
	private SharedService sharedService;
	
	/**
	 * Controlers graphiques
	 */
	private Stage stage;
	
	private Parent root;
	
	private LogController mLogController;
	
	private MainController mMainController;
	
	private TweetsQueuController mTweetsQueuController;
	
	private UsersQueuController mUsersQueuController;
	
	private NewTweetController mNewTweetController;
		

	/**
	 * Constructeur.
	 * @param stage 
	 */
	public Twitup() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();
		
		this.sharedService = new SharedService();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}
	}

	/**
	 * Initialisation des différents controllers de l'application
	 * @return 
	 */
	public void initControllers() {
		initGraphicControllers();
		this.initDirectory();
//		this.mUserController = new UserController(this.mUsersQueuController, this.mEntityManager, this.mDatabase, this.sharedService);
//		this.mTweetController = new TweetController(this.mDatabase, this.mEntityManager, this.mTweetsQueuController, this.sharedService);
		
	}
	
	public void initGui(Stage stage){
		this.mMainController.setCenter(this.mLogController.getComponent());
		this.stage = stage;
		stage.setTitle("TwitUp");
		stage.setScene(new Scene(getRoot(), 300, 275));
        stage.show();
	}
	
	public void initGraphicControllers(){
		this.mMainController = customLoad(MainController.class, "./src/com/iup/tp/twitup/core/ViewAccueil.fxml").getKey();
		this.setRoot(customLoad(MainController.class, "./src/com/iup/tp/twitup/core/ViewAccueil.fxml").getValue());
		
		this.mLogController = customLoad(LogController.class, "./src/com/iup/tp/twitup/core/ViewConnexion.fxml").getKey();
		this.mTweetsQueuController = customLoad(TweetsQueuController.class, "./src/com/iup/tp/twitup/core/ViewTweets.fxml").getKey();
		this.mUsersQueuController = customLoad(UsersQueuController.class, "./src/com/iup/tp/twitup/core/ViewUsers.fxml").getKey();
		this.mNewTweetController = customLoad(NewTweetController.class, "./src/com/iup/tp/twitup/core/ViewNewTweet.fxml").getKey();
	}
	
	public <T> Pair<T, Parent> customLoad(Class<T> classType, String path){
		FXMLLoader loader;
		try {
			loader = new FXMLLoader(new File(path).toURL());
			Parent root = loader.load();
			T ctrlInstance = loader.getController();
			return new Pair<T, Parent>(ctrlInstance, root);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {

		// recuperation du look and feel a partir du fichier de conf
		Properties prop = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String dir = prop.getProperty(Constants.CONFIGURATION_KEY_LOOK_AND_FEEL);

		if (!dir.isEmpty()) {

			try {
				UIManager.setLookAndFeel(dir);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				initDefaultLookAndFeel();
				e.printStackTrace();
			}

		} else {
			// le properties n'a pas pu etre charge
			initDefaultLookAndFeel();
		}
	}

	protected void initDefaultLookAndFeel() {
		String directoryLookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(directoryLookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		Properties prop = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String dir = prop.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY);

		if (!dir.isEmpty()) {
			File directory = new File(dir);
			if (!isValideExchangeDirectory(directory))
				dir = null;
		} else {
			dir = null;
		}

		this.initDirectory(dir);
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		new MockController(this.mDatabase).startMock();
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		if (directoryPath == null) {
			System.out.println("Repertoire non renseigné.");
			if (this.mExchangeDirectoryPath == null){
				do {
					directoryPath = getDirPath();
				} while (directoryPath == null);
				PropertiesManager.updateProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY, directoryPath);
			}
			else
				return;
		} else {
			if (this.mWatchableDirectory != null)
				this.mWatchableDirectory.stopWatching();
			PropertiesManager.updateProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY, directoryPath);
		}

		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	private String getDirPath() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		 directoryChooser.setTitle("Open Resource File");
		 
		 File selectedFile = directoryChooser.showDialog(stage);
		 if(selectedFile != null)
			 return selectedFile.getAbsolutePath();
		 else
			 return null;
	}

	/**
	 * Methode permettant l'ajout d'un observer à la BDD
	 */
	public void addDatabaseObserver(IDatabaseObserver observer) {
		this.mDatabase.addObserver(observer);
	}

	public IDatabase getmDatabase() {
		return mDatabase;
	}

	public EntityManager getmEntityManager() {
		return mEntityManager;
	}

	public UserController getmUserController() {
		return mUserController;
	}

	
	/**
	 * GRAPHICS METHODS
	 */
	public void onUserLogged() {
		// TODO Auto-generated method stub
		
	}

	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}
	


}
