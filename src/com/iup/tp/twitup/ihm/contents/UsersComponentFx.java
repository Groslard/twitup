package com.iup.tp.twitup.ihm.contents;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.datamodel.User;

public class UsersComponentFx extends GridPane {
	static String dateFormat = "dd/MM/yy HH:mm:ss";
	protected UserController userController;
	protected Button btnFollow;
	protected boolean isFollowed = false;

	public UsersComponentFx(User user, UserController userController) {

		this.userController = userController;
		this.setMinSize(370, 50);
		this.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #0084B4;");
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		Label tagLabel = new Label(user.getUserTag());
		tagLabel.setTextFill(Color.web("#3F84B4"));

		Label message = new Label(user.getName());
		message.setTextFill(Color.web("#3F84B4"));

		btnFollow = new Button("Follow");
		this.btnFollow.setStyle("-fx-background-color: #0084B4");
		this.btnFollow.setTextFill(Color.WHITE);

		HBox hbox = new HBox();
		Image image;
		Label label = new Label();
		if (user.getAvatarPath() != null && !user.getAvatarPath().isEmpty()) {
			image = new Image("file:" + user.getAvatarPath(), 25, 25, false, false);
		} else {
			image = new Image("file:./src/resources/images/lamaIcon.png", 25, 25, false, false);
		}
		label.setGraphic(new ImageView(image));
		hbox.setSpacing(10);
		hbox.getChildren().add((label));

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		this.getColumnConstraints().addAll(col1, col2);

		GridPane.setConstraints(hbox, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER);
		GridPane.setConstraints(tagLabel, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER);
		GridPane.setConstraints(btnFollow, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
		GridPane.setConstraints(message, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

		this.minWidth(250);
		this.getChildren().setAll(hbox, tagLabel, message, btnFollow);
		UsersComponentFx composantIntermediaire = this;

		// action follow unfollow
		btnFollow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				userController.followUser(user, composantIntermediaire);
			}
		});

		// animation jfx
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setScaleX(1.025);
				setScaleY(1.025);
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setScaleX(1);
				setScaleY(1);
			}
		});
	}

	/****************** animatiojfx *******************/
	public void hideUser() {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), this);
		fadeTransition.setFromValue(1.0f);
		fadeTransition.setToValue(0.5f);
		fadeTransition.setAutoReverse(true);

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(250), this);
		translateTransition.setFromX(0);
		translateTransition.setToX(-getWidth() / 2);

		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(250), this);
		scaleTransition.setFromX(1);
		scaleTransition.setToX(0);
		scaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setVisible(false);
			}
		});

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeTransition, translateTransition, scaleTransition);
		parallelTransition.play();
	}

	public void showUser() {
		this.setVisible(true);

		FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), this);
		fadeTransition.setFromValue(0f);
		fadeTransition.setToValue(1.0f);
		fadeTransition.setAutoReverse(true);

		ScaleTransition thirdScaleTransition = new ScaleTransition(Duration.millis(100), this);
		thirdScaleTransition.setFromY(0.8);
		thirdScaleTransition.setToY(1);

		ScaleTransition secondScaleTransition = new ScaleTransition(Duration.millis(100), this);
		secondScaleTransition.setFromY(1.2);
		secondScaleTransition.setToY(0.8);
		secondScaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				thirdScaleTransition.play();
			}
		});

		ScaleTransition firstScaleTransition = new ScaleTransition(Duration.millis(200), this);
		firstScaleTransition.setFromY(0);
		firstScaleTransition.setToY(1.2);
		firstScaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				secondScaleTransition.play();
			}
		});

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeTransition, firstScaleTransition);
		parallelTransition.play();
	}

	public Button getBtnFollow() {
		return btnFollow;
	}

	public void setBtnFollow(Button btnFollow) {
		this.btnFollow = btnFollow;
	}

	public boolean isFollowed() {
		return isFollowed;
	}

	public void setFollowed(boolean isFollowed) {
		this.isFollowed = isFollowed;
	}

}
