package com.iup.tp.twitup.ihm.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MenuComponentFx extends GridPane {

	protected ViewControllerJfx viewController;

	protected Button accueilButton;
	protected Button usersButton;
	protected Button profilButton;

	protected Button newTweetButton;
	protected Button rechercheButton;
	protected Button disconnectButton;

	public MenuComponentFx(ViewControllerJfx viewController) {
		this.viewController = viewController;

		this.accueilButton = new Button("Accueil");
		this.add(accueilButton, 0, 1);
		this.accueilButton.setMinWidth(100);
		this.usersButton = new Button("Utilisateurs");
		this.add(usersButton, 0, 2);
		this.usersButton.setMinWidth(100);
		this.profilButton = new Button("Profil");
		this.add(profilButton, 0, 3);
		this.profilButton.setMinWidth(100);
		this.newTweetButton = new Button("Nouveau Tweet");
		this.add(newTweetButton, 0, 4);
		this.newTweetButton.setMinWidth(100);
		this.rechercheButton = new Button("Rechercher");
		this.add(rechercheButton, 0, 5);
		this.rechercheButton.setMinWidth(100);
		this.disconnectButton = new Button("Deconnexion");
		this.add(disconnectButton, 0, 6);
		this.disconnectButton.setMinWidth(100);

		accueilButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onMenuAccueilClicked();

			}
		});

		usersButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onMenuUsersClicked();

			}
		});
		profilButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onMenuProfilClicked();

			}
		});
		newTweetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onMenuNewTweetClicked();

			}
		});
		rechercheButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onMenuRechercheClicked();

			}
		});
		disconnectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onUserDisconnected();

			}
		});

	}

}
