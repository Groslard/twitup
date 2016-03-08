package com.iup.tp.twitup.ihm.connexion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public  abstract class TwiupChoixConnexion extends JPanel{

	protected JLabel labelLogin;
	protected JLabel labelPassword;
	protected JTextField textLogin;
	protected JTextField textPassword;
	protected JButton validation;
	public abstract void showGUI() ;
		
		
	
	
}
