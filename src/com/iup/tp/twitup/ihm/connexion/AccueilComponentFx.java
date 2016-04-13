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
import com.iup.tp.twitup.core.ViewControllerJfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AccueilComponentFx extends GridPane {
	protected Label labelTextAccueil;
	
	protected SwitchConnexionInscriptionComponentFx selectorComponent;
	protected GridPane formPan;
	protected ViewControllerJfx viewController;
	
	
	
	public AccueilComponentFx(ViewControllerJfx viewController) {
		this.viewController = viewController;
		//this.setBackground(Color.WHITE);
		
		//this.setLayout( new GridBagLayout());
		labelTextAccueil=new Label("Bienvenue Sur TWIT TWIT");
		this.add(labelTextAccueil, 0, 0);
//		this.add(labelTextAccueil, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
//				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		this.selectorComponent=new SwitchConnexionInscriptionComponentFx(viewController);
//		
//		this.add(this.selectorComponent, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
//				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
//		
		
		formPan = new GridPane();
		
//		this.add(formPan, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
//				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		
		
	}
	
	public void setFormPan(GridPane newFormPan) {
		this.formPan.getChildren().clear();
		this.formPan.add(newFormPan, 1, 1);
		
		
	}

}
