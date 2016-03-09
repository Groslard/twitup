package com.iup.tp.twitup.ihm.connexion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.core.ViewController;

public class ConnexionComponent extends JPanel {

	protected JLabel labelLogin;
	protected JLabel labelPassword;
	protected JTextField textLogin;
	protected JTextField textPassword;
	protected JButton validation;
	
	protected ViewController viewController;
	
	public ConnexionComponent(ViewController viewController) {
		this.viewController = viewController;
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		labelLogin=new JLabel();
		labelLogin.setText("Login");
		labelPassword=new JLabel();
		labelPassword.setText("Password");
		validation=new JButton("Connexion");
		textLogin=new JTextField(15);
		textPassword=  new JPasswordField(15);
		
		this.add(labelLogin, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		this.add(textLogin, new GridBagConstraints(5, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 10), 0, 0));
	
		this.add(labelPassword,new GridBagConstraints(0, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						10, 5, 0, 5), 0, 0));
		this.add(textPassword,new GridBagConstraints(5, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(
						10, 5, 0, 10), 0, 0));
		this.add(validation,new GridBagConstraints(5, 3, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						10, 5, 0, 5), 0, 0));
		
		this.validation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewController.onUserLogged();
			}
		});
		
	}
}
