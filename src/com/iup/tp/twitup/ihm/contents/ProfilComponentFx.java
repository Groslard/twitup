package com.iup.tp.twitup.ihm.contents;


import javax.swing.JFileChooser;

import com.iup.tp.twitup.core.UserController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ProfilComponentFx extends GridPane {

	protected Text scenetitle;
	protected Label login;
	protected UserController userController;
	protected TextField loginTextField;
	protected Label tag;
	protected Label labTag;
	protected Label pw;
	protected Label labelError;
	protected PasswordField pwBox;
	protected Button btnUpdateProfil;
	protected TextField pathPhoto;
	protected Button pathChooser;
	protected Label labelimg;
 	public ProfilComponentFx(UserController userController) {

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		this.scenetitle = new Text("Profil");
		this.scenetitle.setFill(Color.web("#3F84B4"));
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.scenetitle.setTextAlignment(TextAlignment.CENTER);
		this.add(scenetitle, 0, 0, 2, 1);

		this.labelError = new Label();
		this.add(labelError, 1, 1);
		this.login = new Label("Login:");
		this.login.setTextFill(Color.web("#3F84B4"));
		this.add(login, 0, 2);

		this.loginTextField = new TextField();
		this.add(loginTextField, 1, 2);

		this.tag = new Label("Tag:");
		this.tag.setTextFill(Color.web("#3F84B4"));
		this.add(tag, 0, 3);

		this.labTag = new Label();
		this.add(labTag, 1, 3);

		this.pw = new Label("Password:");
		this.pw.setTextFill(Color.web("#3F84B4"));
		this.add(pw, 0, 4);

		this.pwBox = new PasswordField();
		this.add(pwBox, 1, 4);

		this.btnUpdateProfil = new Button("Modifier");
		this.btnUpdateProfil.setStyle("-fx-background-color: #0084B4");
		this.btnUpdateProfil.setTextFill(Color.WHITE);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnUpdateProfil);
		this.add(hbBtn, 1, 6);

		
		Image  image = new Image("file:./src/resources/images/lamaIcon.png", 25, 25, false, false);
		labelimg = new Label();
		labelimg.setGraphic(new ImageView(image));
		

		this.add(labelimg, 0, 5);
		this.pathPhoto = new TextField();
		this.add(pathPhoto, 1, 5);
		this.pathChooser= new Button("Selectionner");
		this.pathChooser.setStyle("-fx-background-color: #0084B4");
		this.pathChooser.setTextFill(Color.WHITE);
		this.add(pathChooser, 2, 5);
		
		
		pathChooser.setOnAction(new EventHandler<ActionEvent>(){
             @Override
            public void handle(ActionEvent arg0) {
            	final JFileChooser fc = new JFileChooser();
         		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
         		fc.showOpenDialog(fc);
         		pathPhoto.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });
		
		
		btnUpdateProfil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				userController.updateUserProfil(loginTextField.getText(), pwBox.getText(), pathPhoto.getText());
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
		this.labTag.setText("");
	}

	public TextField getLoginTextField() {
		return loginTextField;
	}

	public void setLoginTextField(TextField loginTextField) {
		this.loginTextField = loginTextField;
	}

	public Label getLabTag() {
		return labTag;
	}

	public void setLabTag(Label labTag) {
		this.labTag = labTag;
	}

	public PasswordField getPwBox() {
		return pwBox;
	}

	public void setPwBox(PasswordField pwBox) {
		this.pwBox = pwBox;
	}

	public TextField getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(TextField pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public Label getLabelimg() {
		return labelimg;
	}

	public void setLabelimg(Label labelimg) {
		this.labelimg = labelimg;
	}

}
