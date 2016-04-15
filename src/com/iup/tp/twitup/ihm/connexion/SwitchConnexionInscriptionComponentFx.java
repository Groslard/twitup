package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class SwitchConnexionInscriptionComponentFx extends GridPane {

	protected ViewControllerJfx viewController;
	protected Button btnConnexion;
	protected Button btnInscritpion;

	public SwitchConnexionInscriptionComponentFx(ViewControllerJfx viewControlle) {
		
		this.viewController= viewControlle;
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.btnConnexion = new Button("Connexion");
		this.btnInscritpion = new Button("Inscription");
		
		this.btnConnexion.setStyle("-fx-background-color: #0084B4");
		this.btnInscritpion.setStyle("-fx-background-color: #0084B4");
		this.btnConnexion.setTextFill(Color.WHITE);
		this.btnInscritpion.setTextFill(Color.WHITE);
		this.btnConnexion.setMinSize(60, 30);
		this.btnInscritpion.setMinSize(60, 30);
		GridPane.setConstraints(btnConnexion, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER,new Insets(0, 0, 30, 0));
		GridPane.setConstraints(btnInscritpion, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
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
