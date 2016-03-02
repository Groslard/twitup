package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.common.ConfigurationInterface;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView implements IDatabaseObserver
{
	private ConfigurationInterface confInterface;
	
	protected JFrame mFrame;
	
	JMenuBar menuBar;
	JMenu ficMenu, helpMenu;
	JMenuItem aPropos, quitter, changeDirectory;
	
	public TwitupMainView(ConfigurationInterface ci) {
		this.confInterface = ci;
	}


	public void showGUI() {
		// Init auto de l'IHM
		if (mFrame == null) {
			this.initGUI();
		}
		
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitupMainView.this.mFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
						.getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				mFrame.setMinimumSize(new Dimension(screenSize.width/2, 
						screenSize.height/2));
				
				// Affichage
				TwitupMainView.this.mFrame.setVisible(true);

				TwitupMainView.this.mFrame.pack();
			}
		});
	}
	
	
	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		// Création de la fenetre principale
		mFrame = new JFrame("twItUP");
		
		
		//Création de la menuBar
		
		// Menu Fichier
		menuBar = new JMenuBar();
		
		ficMenu = new JMenu("Fichier");
		
		changeDirectory = new JMenuItem("Change Directory");
		changeDirectory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newPath = TwitupMainView.this.chooseDirPath();
				TwitupMainView.this.confInterface.changeDirectory(newPath);
			}
		});
		
		quitter = new JMenuItem("Quitter", new ImageIcon("./src/resources/images/exitIcon_20.png"));
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
				JOptionPane.showMessageDialog(mFrame, text, title, JOptionPane.INFORMATION_MESSAGE ,icon );
			}
		});
		
		
		// Ajout des menu à la menu bar
		menuBar.add(ficMenu);
		menuBar.add(helpMenu);
		
		// Ajout du menu à la frame		
		mFrame.setJMenuBar(menuBar);
		mFrame.setLayout(new GridBagLayout());
	}
	
	public String chooseDirPath(){
		  final JFileChooser fc = new JFileChooser();
		  
		  fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
		  
		  int returnVal = fc.showOpenDialog(fc);

		  if (returnVal == JFileChooser.APPROVE_OPTION) {
			  // On recupere le path selectionné
			  File file = fc.getSelectedFile();
			  return file.getAbsolutePath();
		  } else {
			  return null;
		  }
	}
	
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println(addedTwit.getTwiter().getName() + "|" + addedTwit.getText());
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
