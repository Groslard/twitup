package com.iup.tp.twitup.core;

import java.io.File;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMock;

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
	protected boolean mIsMockEnabled = true;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	protected ViewController mViewController;
	
	protected TweetController mTweetController;

	protected UserController mUserController;

	/**
	 * Constructeur.
	 */
	public Twitup() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}

		// Initialisation des controllers
		this.mViewController = new ViewController(this);

		// Initialisation du répertoire d'échange
		this.initDirectory();
		
		this.initControllers();
		
	}

	/**
	 * Initialisation des différents controllers de l'application
	 */
	public void initControllers() {
		this.mViewController = new ViewController(this);
		this.mUserController = new UserController(this.mViewController, this.mEntityManager, this.mDatabase);
		this.mTweetController = new TweetController(this.mDatabase, this.mEntityManager, this.mViewController);

		this.mUserController.loadUsers();
		
	
	}
	
	public void initGui() {
		this.mViewController.initGui();
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
				dir = this.mViewController.getDirPath();
		} else {
			dir = this.mViewController.getDirPath();
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
			if (this.mExchangeDirectoryPath == null)
				do {
					directoryPath = this.mViewController.getDirPath();
				} while (directoryPath == null);
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

	public ViewController getmViewController() {
		return mViewController;
	}

	public UserController getmUserController() {
		return mUserController;
	}

}
