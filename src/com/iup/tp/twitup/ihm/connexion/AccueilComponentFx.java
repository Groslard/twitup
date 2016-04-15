package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AccueilComponentFx extends GridPane {
	protected Label labelTextAccueil;

	protected GridPane selectorComponent;
	protected GridPane formPan;
	protected ViewControllerJfx viewController;
	protected Label messageAccueil;

	public AccueilComponentFx(ViewControllerJfx viewController) {

		//initialisation et parametrage des composants graphiques
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		Image image = new Image("file:./src/resources/images/pigeon.png", 100, 100, false, false);

		this.messageAccueil = new Label("Bienveue sur TwitTwit");
		this.messageAccueil.setGraphic(new ImageView(image));
		this.messageAccueil.setTextFill(Color.web("#3F84B4"));
		this.messageAccueil.setFont(new Font(30));
		GridPane.setHgrow(messageAccueil, Priority.ALWAYS);
		GridPane.setVgrow(messageAccueil, Priority.ALWAYS);
		GridPane.setConstraints(messageAccueil, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);

		//contrainte pour que les deux coloes soient égales
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		this.getColumnConstraints().addAll(col1, col2);
		
		this.selectorComponent = viewController.getSwitchCompAccueil();
		this.formPan = viewController.getCompConnexion();
		
		// contrainte de resize et positionnement
		GridPane.setHgrow(selectorComponent, Priority.ALWAYS);
		GridPane.setVgrow(selectorComponent, Priority.ALWAYS);
		GridPane.setHgrow(formPan, Priority.ALWAYS);
		GridPane.setVgrow(formPan, Priority.ALWAYS);
		GridPane.setConstraints(selectorComponent, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(formPan, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

		//ajout des composants
		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);

	}

	/******Methode  de changemet de composant*******/
	
	public void setFormPan(GridPane newFormPan) {
		this.formPan = newFormPan;
		GridPane.setConstraints(formPan, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setHgrow(formPan, Priority.ALWAYS);
		GridPane.setVgrow(formPan, Priority.ALWAYS);
		this.getChildren().clear();
		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);

	}

	public void setSwitchConnexion(GridPane newFormPan) {
		this.selectorComponent = newFormPan;
		GridPane.setConstraints(selectorComponent, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setHgrow(selectorComponent, Priority.ALWAYS);
		GridPane.setVgrow(selectorComponent, Priority.ALWAYS);
		this.getChildren().clear();
		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);
	}
}
