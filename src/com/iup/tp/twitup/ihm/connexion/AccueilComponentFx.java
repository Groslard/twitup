package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class AccueilComponentFx extends GridPane {
	protected Label labelTextAccueil;

	protected GridPane selectorComponent;
	protected GridPane formPan;
	protected ViewControllerJfx viewController;
	protected Label messageAccueil;

	public AccueilComponentFx(ViewControllerJfx viewController) {
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.messageAccueil = new Label("Bienveue sur TwitTwit");
		GridPane.setConstraints(messageAccueil, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);

		this.getColumnConstraints().addAll(col1, col2);
		this.selectorComponent = viewController.getSwitchCompAccueil();
		this.formPan = viewController.getCompConnexion();
		GridPane.setConstraints(selectorComponent, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(formPan, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);

	}

	public void setFormPan(GridPane newFormPan) {
		this.formPan = newFormPan;
		GridPane.setConstraints(formPan, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		this.getChildren().clear();
		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);

	}

	public void setSwitchConnexion(GridPane newFormPan) {
		this.selectorComponent = newFormPan;
		GridPane.setConstraints(selectorComponent, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		this.getChildren().clear();
		this.getChildren().setAll(messageAccueil, formPan, selectorComponent);
	}
}
