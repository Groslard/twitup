package com.iup.tp.twitup.ihm.contents;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.iup.tp.twitup.datamodel.Twit;

public class TweetComponentFx extends GridPane {
	static String dateFormat = "dd/MM/yy HH:mm:ss";
	public TweetComponentFx(Twit twit) {

		//this.setMinSize(370, 50);

		this.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
		this.setMaxWidth(Double.MAX_VALUE);
		Label tagLabel = new Label(twit.getTwiter().getUserTag());
		Label message = new Label(twit.getText());

		
		HBox hbox = new HBox();
	    //Replace the image you want to put up
	    Image image = new Image(getClass().getResourceAsStream("lamaIcon.png"), 25, 25, false, false);
	    Label label = new Label();
	    label.setGraphic(new ImageView(image));
	    hbox.setSpacing(10);
	    hbox.getChildren().add((label));
		
	    //gestion date
	    Date date=new Date(twit.getEmissionDate());
        SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
        String dateText = df2.format(date);
	    Label labelDate=new Label(dateText);
	    GridPane.setConstraints(hbox, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER);
	    GridPane.setConstraints(labelDate, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
	    GridPane.setConstraints(tagLabel, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER);
	    GridPane.setConstraints(message, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);
		this.minWidth(250);
		this.getChildren().setAll(hbox, labelDate, tagLabel,message);
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

	public void hideTwit() {
		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(250), this);
		fadeTransition.setFromValue(1.0f);
		fadeTransition.setToValue(0.5f);
		fadeTransition.setAutoReverse(true);

		TranslateTransition translateTransition = new TranslateTransition(
				Duration.millis(250), this);
		translateTransition.setFromX(0);
		translateTransition.setToX(-getWidth()  /2 );
		
		ScaleTransition scaleTransition = new ScaleTransition(
				Duration.millis(250), this);
		scaleTransition.setFromX(1);
		scaleTransition.setToX(0);
		scaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setVisible(false);
			}
		});

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren()
				.addAll(fadeTransition, translateTransition, scaleTransition);
		parallelTransition.play();
	}

	public void showTwit() {
		this.setVisible(true);

		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(500), this);
		fadeTransition.setFromValue(0f);
		fadeTransition.setToValue(1.0f);
		fadeTransition.setAutoReverse(true);

		ScaleTransition thirdScaleTransition = new ScaleTransition(
				Duration.millis(100), this);
		thirdScaleTransition.setFromY(0.8);
		thirdScaleTransition.setToY(1);

		ScaleTransition secondScaleTransition = new ScaleTransition(
				Duration.millis(100), this);
		secondScaleTransition.setFromY(1.2);
		secondScaleTransition.setToY(0.8);
		secondScaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				thirdScaleTransition.play();
			}
		});

		ScaleTransition firstScaleTransition = new ScaleTransition(
				Duration.millis(200), this);
		firstScaleTransition.setFromY(0);
		firstScaleTransition.setToY(1.2);
		firstScaleTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				secondScaleTransition.play();
			}
		});

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeTransition,
				firstScaleTransition);
		parallelTransition.play();
	}

}
