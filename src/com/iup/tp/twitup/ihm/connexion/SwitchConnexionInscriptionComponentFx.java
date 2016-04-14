package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SwitchConnexionInscriptionComponentFx extends GridPane {

	protected ViewControllerJfx viewController;
	protected Button btnConnexion;
	protected Button btnInscritpion;

	public SwitchConnexionInscriptionComponentFx(ViewControllerJfx viewControlle) {
		
		this.viewController= viewControlle;
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.btnConnexion = new Button("Connexion");
		this.btnInscritpion = new Button("Inscription");
		this.btnConnexion.setMinSize(60, 30);
		this.btnInscritpion.setMinSize(60, 30);
		GridPane.setConstraints(btnConnexion, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(btnInscritpion, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		this.setAlignment(Pos.CENTER);
		this.getChildren().setAll(btnConnexion,btnInscritpion);
		btnConnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				viewController.onLogConnexionClicked();
			}
		});

		btnInscritpion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				viewController.onLogInscriptionClicked();
			}
		});
		

	}

}
