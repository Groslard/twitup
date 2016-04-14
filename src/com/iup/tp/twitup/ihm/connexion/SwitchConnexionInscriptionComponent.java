package com.iup.tp.twitup.ihm.connexion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.ViewController;

public class SwitchConnexionInscriptionComponent extends JPanel {

	protected JButton bConnexion;
	protected JButton bInscription;
	
	protected ViewController viewController;
	

	public SwitchConnexionInscriptionComponent(ViewController viewController) {
		this.viewController = viewController;
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		this.bConnexion = new JButton("Connexion");
		this.bInscription = new JButton("Inscription");
		this.bConnexion.setPreferredSize(new Dimension(100, 50));
		this.bInscription.setPreferredSize(new Dimension(100, 50));

		this.bInscription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewController.onLogInscriptionClicked();
			}
		});
		
		this.bConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewController.onLogConnexionClicked();
			}
		});
		
		
		this.add(bConnexion, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						5, 5, 5, 5), 0, 0));
		this.add(bInscription, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		
	}



}
