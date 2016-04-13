package com.iup.tp.twitup.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.core.ViewControllerJfx;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.mock.jfx.MockTwitListComponentJFX;
import com.iup.tp.twitup.mock.jfx.MockTwitSearchComponentJFX;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainViewFx implements IDatabaseObserver {
	private ViewControllerJfx viewController;

	protected JFXPanel mJFXpanel;
	
	protected BorderPane mPan;
	
	protected GridPane leftPan;
	protected GridPane centerPan;
	protected GridPane rightPan;
	
	/** Menu Bar **/
//	JMenuBar menuBar;
//	JMenu ficMenu, helpMenu;
//	JMenuItem aPropos, quitter, changeDirectory;

	public TwitupMainViewFx(ViewControllerJfx viewController) {
		this.viewController = viewController;
	}

	public void showGUI() {
		// Init auto de l'IHM
		if (mJFXpanel == null) {
			this.initGUI();
		}

		mJFXpanel = new JFXPanel();

		// Création des composants graphiques JavaFX
	
		mPan=new BorderPane();
	
		
		// Création du pont entre JavaFX et SWING
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				Scene scene = new Scene(mPan, 350, 350);
				mJFXpanel.setScene(scene);
			}
		});
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		// Création de la fenetre principale
		

		// Ajout du menu à la frame
	
		
		
		
		leftPan = new GridPane();
		centerPan =new GridPane();
		rightPan =new GridPane();
		
		mPan.setCenter(centerPan);
		mPan.setLeft(leftPan);
		mPan.setRight(rightPan);
	}
	
	

	public void setLeftPan(Gridpane newLeftPan) {
		this.leftPan.
		if(newLeftPan != null)
			this.leftPan.add(newLeftPan,  new GridBagConstraints(0, 0, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(5, 5, 5, 5), 0, 0));
		ViewController.updatePan(this.leftPan);
	}
	
	public void setCenterPan(GridPane component) {
		this.centerPan.removeAll();
		if(component != null)
			this.centerPan.add(component,  new GridBagConstraints(0, 0, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(0, 0, 0, 0), 0, 0));
		ViewController.updatePan(this.centerPan);
	}
	
	public void setRightPan(JPanel newRightPan) {
		this.rightPan.removeAll();
		if(newRightPan != null)
			this.rightPan.add(newRightPan,  new GridBagConstraints(0, 0, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(5, 5, 5, 5), 0, 0));
		ViewController.updatePan(this.rightPan);
	}
	
	
	
	public JMenuBar buildMenuBar() {
		// Menu Fichier
		menuBar = new JMenuBar();

		ficMenu = new JMenu("Fichier");

		changeDirectory = new JMenuItem("Change Directory");
		changeDirectory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TwitupMainViewFx.this.viewController.updateExchangeDirectory();
			}
		});

		quitter = new JMenuItem("Quitter", new ImageIcon(
				"./src/resources/images/exitIcon_20.png"));
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		ficMenu.add(changeDirectory);
		ficMenu.add(quitter);

		// Menu Aide
		helpMenu = new JMenu("Aide");
		aPropos = new JMenuItem("A Propos");
		helpMenu.add(aPropos);

		String text = "UBO M2-TIIL 2016\nDépartement Informatique";
		String title = "A propos";
		Icon icon = new ImageIcon("./src/resources/images/logoIUP_50.jpg");

		aPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mFrame, text, title,
						JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});

		// Ajout des menu à la menu bar
		menuBar.add(ficMenu);
		menuBar.add(helpMenu);
		
		return menuBar;
	}

	
	public File showGuiDirPath() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(fc);
		return fc.getSelectedFile();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub

	}


}