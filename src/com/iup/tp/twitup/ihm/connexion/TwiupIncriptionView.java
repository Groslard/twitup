package com.iup.tp.twitup.ihm.connexion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TwiupIncriptionView extends TwiupChoixConnexion{
	protected JLabel labelTag;
	protected JTextField textTag;
	
	
	public String getPassword() {
		String password = "";
		return password;
	}

	public String getLogin() {
		String login = "";
		return login;
	}

	public void showGUI() {

		if (labelLogin == null) {
			this.initGUI();
		}
		
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage

				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				labelLogin.setSize(screenSize.width / 4, screenSize.height / 4);
				labelPassword.setSize(screenSize.width / 4, screenSize.height / 4);
				labelTag.setSize(screenSize.width / 4, screenSize.height / 4);
				labelTag.setSize(screenSize.width / 4, screenSize.height / 4);
				textLogin.setSize(screenSize.width / 4, screenSize.height / 4);
				textPassword.setSize(screenSize.width / 4, screenSize.height / 4);
				validation.setSize(screenSize.width / 4, screenSize.height / 4);
				// Affichage

			}
		});
		
		this.setVisible(true);
	}

	protected void initGUI() {
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		labelLogin=new JLabel();
		labelLogin.setText("Login");
		labelTag=new JLabel();
		labelTag.setText("Tag");
		labelPassword=new JLabel();
		labelPassword.setText("Password");
		validation=new JButton("Inscription");
		textLogin=new JTextField(15);
		textTag=new JTextField(15);
		textPassword=  new JPasswordField(15);
		
		this.add(labelLogin, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		this.add(textLogin, new GridBagConstraints(5, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 10), 0, 0));
	
		this.add(labelTag,new GridBagConstraints(0, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						10, 5, 0, 5), 0, 0));
		this.add(textTag,new GridBagConstraints(5, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(
						10, 5, 0, 10), 0, 0));
		
		
		this.add(labelPassword,new GridBagConstraints(0, 3, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						10, 5, 0, 5), 0, 0));
		this.add(textPassword,new GridBagConstraints(5, 3, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(
						10, 5, 0, 10), 0, 0));
		this.add(validation,new GridBagConstraints(5, 4, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						10, 5, 0, 5), 0, 0));
		
		
		

	}
	
	public static void main(String[] args) {
		
		
		 JFrame f = new JFrame();
		    f.setSize( 250, 150);
		    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    f.setVisible(true);
		   
		
		//test du composant 
		    TwiupIncriptionView connection= new TwiupIncriptionView();
		connection.initGUI();
		 f.getContentPane().add(connection);
		 f.pack();
	}
}
