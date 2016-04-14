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
import javafx.scene.layout.Priority;

public class MenuComponentFx extends GridPane {

	protected ViewControllerJfx viewController;

	protected Button accueilButton;
	protected Button usersButton;
	protected Button profilButton;

	protected Button newTweetButton;
	protected Button disconnectButton;

	public MenuComponentFx(ViewControllerJfx viewController) {
		this.viewController = viewController;
		
		this.accueilButton = new Button("Accueil");
		this.add(accueilButton, 0, 1);
		this.accueilButton.setMinWidth(100);
		this.accueilButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(accueilButton, Priority.ALWAYS);
		GridPane.setVgrow(accueilButton, Priority.ALWAYS);
		
		
		this.usersButton = new Button("Utilisateurs");
		this.add(usersButton, 0, 2);
		this.usersButton.setMinWidth(100);
		this.usersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(usersButton, Priority.ALWAYS);
		GridPane.setVgrow(usersButton, Priority.ALWAYS);
		
		this.profilButton = new Button("Profil");
		this.add(profilButton, 0, 3);
		this.profilButton.setMinWidth(100);
		this.profilButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(profilButton, Priority.ALWAYS);
		GridPane.setVgrow(profilButton, Priority.ALWAYS);
		
		
		this.newTweetButton = new Button("Nouveau Tweet");
		this.add(newTweetButton, 0, 4);
		this.newTweetButton.setMinWidth(100);
		this.newTweetButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(newTweetButton, Priority.ALWAYS);
		GridPane.setVgrow(newTweetButton, Priority.ALWAYS);
		
		
		this.disconnectButton = new Button("Deconnexion");
		this.add(disconnectButton, 0, 6);
		this.disconnectButton.setMinWidth(100);
		this.disconnectButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(disconnectButton, Priority.ALWAYS);
		GridPane.setVgrow(disconnectButton, Priority.ALWAYS);

		
		
		
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
		disconnectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuComponentFx.this.viewController.onUserDisconnected();

			}
		});

	}

}
