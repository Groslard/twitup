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
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView implements IDatabaseObserver {
	private ViewController viewController;

	protected JFrame mFrame;
	
	protected JPanel mPan;
	
	protected JPanel leftPan;
	protected JPanel centerPan;
	protected JPanel rightPan;
	
	/** Menu Bar **/
	JMenuBar menuBar;
	JMenu ficMenu, helpMenu;
	JMenuItem aPropos, quitter, changeDirectory;

	public TwitupMainView(ViewController viewController) {
		this.viewController = viewController;
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

				mFrame.setMinimumSize(new Dimension(screenSize.width / 2,
						screenSize.height / 2));

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

		// Ajout du menu à la frame
		mFrame.setJMenuBar(buildMenuBar());
		
		this.mPan = new JPanel();
		this.mFrame.setContentPane(mPan);
		
		mPan.setLayout(new BorderLayout());
		
		leftPan = new JPanel();
		leftPan.setLayout(new GridBagLayout());
		centerPan = new JPanel();
		centerPan.setLayout(new GridBagLayout());
		rightPan = new JPanel();
		rightPan.setLayout(new GridBagLayout());
		
		mPan.add(leftPan, BorderLayout.WEST);
		mPan.add(centerPan, BorderLayout.CENTER);
		mPan.add(rightPan, BorderLayout.EAST);
	}
	
	

	public void setLeftPan(JPanel newLeftPan) {
		this.leftPan.removeAll();
		if(newLeftPan != null)
			this.leftPan.add(newLeftPan,  new GridBagConstraints(0, 0, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(5, 5, 5, 5), 0, 0));
		ViewController.updatePan(this.leftPan);
	}
	
	public void setCenterPan(JComponent component) {
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
				TwitupMainView.this.viewController.updateExchangeDirectory();
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
