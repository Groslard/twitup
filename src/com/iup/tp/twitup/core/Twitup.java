package com.iup.tp.twitup.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.ConfigurationInterface;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup
{
  /**
   * Base de données.
   */
  protected IDatabase mDatabase;

  /**
   * Gestionnaire des entités contenu de la base de données.
   */
  protected EntityManager mEntityManager;

  /**
   * Vue principale de l'application.
   */
  protected TwitupMainView mMainView;

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

  /**
   * Constructeur.
   */
  public Twitup()
  {
    // Init du look and feel de l'application
    this.initLookAndFeel();

    // Initialisation de la base de données
    this.initDatabase();

    if (this.mIsMockEnabled)
    {
      // Initialisation du bouchon de travail
      this.initMock();
    }

    // Initialisation de l'IHM
    this.initGui();

    // Initialisation du répertoire d'échange
    this.initDirectory();
  }

  /**
   * Initialisation du look and feel de l'application.
   */
  protected void initLookAndFeel()
  {
	  try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  /**
   * Initialisation de l'interface graphique.
   */
  protected void initGui()
  {
	  
	  ConfigurationInterface ci = new ConfigurationInterface(this);
	  
    this.mMainView = new TwitupMainView(ci);
    
    this.mMainView.showGUI();
    this.mDatabase.addObserver(this.mMainView);
  }

  /**
   * Initialisation du répertoire d'échange (depuis la conf ou depuis un file chooser). <br/>
   * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de pouvoir utiliser l'application</b>
   */
  protected void initDirectory()
  {
	  /*
	   * TODO : pour le moment je passe le directory en dur, par la suite il faudra le config par IHM
	   */
	  Properties prop = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
	  String dir = prop.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY);
	  if(dir.isEmpty()){
		  String dirPath = this.mMainView.chooseDirPath();
		  this.changeDirectory(dirPath);
	  }else{
		  this.initDirectory(dir);
	  }
  }

  /**
   * Indique si le fichier donné est valide pour servire de répertoire d'échange
   * 
   * @param directory
   *          , Répertoire à tester.
   */
  protected boolean isValideExchangeDirectory(File directory)
  {
    // Valide si répertoire disponible en lecture et écriture
    return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
        && directory.canWrite();
  }

  /**
   * Initialisation du mode bouchoné de l'application
   */
  protected void initMock()
  {
    TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
    mock.showGUI();
  }

  /**
   * Initialisation de la base de données
   */
  protected void initDatabase()
  {
    mDatabase = new Database();
    mEntityManager = new EntityManager(mDatabase);
  }

  /**
   * Initialisation du répertoire d'échange.
   * 
   * @param directoryPath
   */
  public void initDirectory(String directoryPath)
  {
    mExchangeDirectoryPath = directoryPath;
    mWatchableDirectory = new WatchableDirectory(directoryPath);
    mEntityManager.setExchangeDirectory(directoryPath);

    mWatchableDirectory.initWatching();
    mWatchableDirectory.addObserver(mEntityManager);
  }
  
  public void changeDirectory(String newPath){
	  if(newPath == null){
		  System.out.println("Repertoire non renseigné.");
		  if(this.mExchangeDirectoryPath == null)
			  System.exit(0);
	  }else{
		  if(this.mWatchableDirectory != null)
			  this.mWatchableDirectory.stopWatching();
		  PropertiesManager.updateProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY, newPath);
		  this.initDirectory(newPath);
	  }
  }
}
