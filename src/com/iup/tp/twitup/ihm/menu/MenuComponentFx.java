package com.iup.tp.twitup.ihm.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.core.ViewControllerJfx;

public class MenuComponentFx extends JPanel {

	protected ViewControllerJfx viewController;
	
	protected JButton accueilButton;
	protected JButton usersButton;
	protected JButton profilButton;
	
	protected JButton newTweetButton;
	protected JButton rechercheButton;
	protected JButton disconnectButton;
	
	public MenuComponentFx(ViewControllerJfx viewController){
		this.viewController = viewController;
		
		this.accueilButton = new JButton("Accueil");
		this.usersButton = new JButton("Utilisateurs");
		this.profilButton = new JButton("Profil");
		
		this.newTweetButton = new JButton("Nouveau Tweet");
		this.rechercheButton = new JButton("Rechercher");
		this.disconnectButton = new JButton("Deconnexion");
		
		this.setLayout(new GridBagLayout());
		this.add(this.accueilButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		this.add(this.usersButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		this.add(this.profilButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		this.add(this.newTweetButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		this.add(this.rechercheButton, new GridBagConstraints(0, 4, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		this.add(this.disconnectButton, new GridBagConstraints(0, 5, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		accueilButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuAccueilClicked();
			}
		});
		usersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuUsersClicked();
			}
		});
		profilButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuProfilClicked();
			}
		});
		newTweetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuNewTweetClicked();
			}
		});
		rechercheButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuRechercheClicked();
			}
		});
		disconnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuComponentFx.this.viewController.onMenuDisconnectClicked();
			}
		});
		
	}
	
}
