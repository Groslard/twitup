package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class AccueilComponentFx extends GridPane {
	protected Label labelTextAccueil;
	
	protected SwitchConnexionInscriptionComponentFx selectorComponent;
	protected GridPane formPan;
	protected ViewControllerJfx viewController;
	
	
	
	public AccueilComponentFx(ViewControllerJfx viewController) {
		
		 GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Welcome");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);

	        Label userName = new Label("User Name:");
	        grid.add(userName, 0, 1);

	        TextField userTextField = new TextField();
	        grid.add(userTextField, 1, 1);

	        Label pw = new Label("Password:");
	        grid.add(pw, 0, 2);

	        PasswordField pwBox = new PasswordField();
	        grid.add(pwBox, 1, 2);

	        Button btn = new Button("Sign in");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(btn);
	        grid.add(hbBtn, 1, 4);

	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 0, 6);
	        grid.setColumnSpan(actiontarget, 2);
	       // grid.setHalignment(actiontarget, RIGHT);
	        actiontarget.setId("actiontarget");

	        btn.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                actiontarget.setFill(Color.FIREBRICK);
	                actiontarget.setText("Sign in button pressed");
	            }
	        });
		
//		this.viewController = viewController;
//		//this.setBackground(Color.WHITE);
//		
//		//this.setLayout( new GridBagLayout());
//		labelTextAccueil=new Label("Bienvenue Sur TWIT TWIT");
//		this.add(labelTextAccueil, 0, 0);
////		this.add(labelTextAccueil, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
////				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
//		
//		this.selectorComponent=new SwitchConnexionInscriptionComponentFx(viewController);
////		
////		this.add(this.selectorComponent, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
////				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
////		
//		
//		formPan = new GridPane();
//		
////		this.add(formPan, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
////				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
//		
		
	}
	
	public void setFormPan(GridPane newFormPan) {
		this.formPan.getChildren().clear();
		this.formPan.add(newFormPan, 1, 1);
		
		
	}

}
