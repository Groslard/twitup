package com.iup.tp.twitup.ihm.connexion;

import com.iup.tp.twitup.core.UserController;

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

public class ConnexionComponentFx extends GridPane {

	protected Text scenetitle;
	protected Label login;
	protected UserController userController;
	protected TextField loginTextField;
	protected Label tag;
	protected TextField tagTextField;
	protected Label labelError;
	protected Label pw;
	protected PasswordField pwBox;
	protected Button btnConnexion;

	public ConnexionComponentFx(UserController userController) {

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		this.scenetitle = new Text("Connexion");
		this.scenetitle.setFill(Color.web("#3F84B4"));
		
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.add(scenetitle, 0, 0, 2, 1);

		this.labelError = new Label();
		this.add(labelError, 1, 1);
		this.login = new Label("Login:");
		this.login.setTextFill(Color.web("#3F84B4"));
		this.add(login, 0, 2);

		this.loginTextField = new TextField();
		this.add(loginTextField, 1, 2);

		this.pw = new Label("Password:");
		this.pw.setTextFill(Color.web("#3F84B4"));
		this.add(pw, 0, 3);

		this.pwBox = new PasswordField();
		this.add(pwBox, 1, 3);

		this.btnConnexion = new Button("Connexion");
		this.btnConnexion.setStyle("-fx-background-color: #0084B4");
		this.btnConnexion.setTextFill(Color.WHITE);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnConnexion);
		this.add(hbBtn, 1, 5);

		

		btnConnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				userController.onUserLogged(loginTextField.getText(), pwBox.getText());
			}
		});

	}

	public void setErrorMessage(String errorMessage) {
		this.labelError.setText(errorMessage);
		this.labelError.setTextFill(Color.RED);
	}

	public void clear() {
		this.labelError.setText("");
		this.pwBox.setText("");
		this.loginTextField.setText("");
		
	}

}
