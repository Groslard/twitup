package com.iup.tp.twitup.ihm.contents;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import com.iup.tp.twitup.core.TweetController;
import com.iup.tp.twitup.datamodel.User;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NewTweetComponentFx extends GridPane {

	private TweetController mTweetController;
	private Button send;

	public NewTweetComponentFx(User connectedUser, TweetController mTweetController){
		super();
		
		this.mTweetController = mTweetController;
		this.setMaxWidth(200);
		
		TextArea editorPane = new TextArea();
		this.add(editorPane, 0, 0);
		editorPane.setWrapText(true);
		
		editorPane.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (editorPane.getText().length() > 150) {
	            	editorPane.positionCaret(editorPane.getCaretPosition()-10);
	                editorPane.setText(oldValue);
	                
	            }
	        }
	    });
		

		send = new Button("Envoyer Tweet");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(send);
		this.add(hbBtn, 0, 1);
		
		send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				NewTweetComponentFx.this.mTweetController.addTweet(editorPane.getText());
				editorPane.setText("");
			}
		});
	}
}
