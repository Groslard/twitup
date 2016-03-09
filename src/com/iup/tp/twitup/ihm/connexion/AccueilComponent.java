package com.iup.tp.twitup.ihm.connexion;

import java.awt.Color;
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

import com.iup.tp.twitup.core.ViewController;

public class AccueilComponent extends JPanel {
	protected JLabel labelTextAccueil;
	
	protected SwitchConnexionInscriptionComponent selectorComponent;
	protected JPanel formPan;
	protected ViewController viewController;
	
	
	
	public AccueilComponent(ViewController viewController) {
		this.viewController = viewController;
		
		this.setBackground(Color.WHITE);
		this.setLayout( new GridBagLayout());
		
		labelTextAccueil=new JLabel();
		labelTextAccueil.setText("Bienvenue Sur TWIT TWIT");
		
		this.add(labelTextAccueil, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		this.selectorComponent=new SwitchConnexionInscriptionComponent(viewController);
		
		this.add(this.selectorComponent, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
		formPan = new JPanel();
		formPan.setLayout(new GridBagLayout());
		
		this.add(formPan, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
	}
	
	public void setFormPan(JPanel newFormPan) {
		this.formPan.removeAll();
		this.formPan.add(newFormPan,  new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 0));
		ViewController.updatePan(this);
	}

}
