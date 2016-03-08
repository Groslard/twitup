package com.iup.tp.twitup.ihm.connexion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TwitupAccueilConnexion extends JPanel {
	protected JLabel labelTextAccueil;
	protected TwiupChoixConnexion choixConnexion;
	protected SelectionConnexion selectConnexion;
	
	
	public String getPassword() {
		String password = "";
		return password;
	}

	public String getLogin() {
		String login = "";
		return login;
	}

	public void showGUI() {

		if (labelTextAccueil == null) {
			this.initGUI();
		}
		
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage

				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				labelTextAccueil.setSize(screenSize.width / 4, screenSize.height / 4);
				// Affichage

			}
		});
		
		this.setVisible(true);
	}

	protected void initGUI() {
		this.setLayout( new GridBagLayout());
		this.setVisible(true);
		labelTextAccueil=new JLabel();
		labelTextAccueil.setText("Bienvenue Sur TWIT TWIT");
		this.add(labelTextAccueil, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
		this.selectConnexion=new  SelectionConnexion();
		
		this.selectConnexion.showGUI();
		this.add(this.selectConnexion, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
		choixConnexion= new TwitupConnexionView();
		choixConnexion.showGUI();
		
		this.add(choixConnexion, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
		
		

	}
	
	public static void main(String[] args) {
		
		
		 JFrame f = new JFrame();
		    f.setSize( 250, 150);
		    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    f.setVisible(true);
		   
		
		//test du composant 
		    TwitupAccueilConnexion connection= new TwitupAccueilConnexion();
		connection.initGUI();
		 f.getContentPane().add(connection);
		 f.pack();
	}
}
