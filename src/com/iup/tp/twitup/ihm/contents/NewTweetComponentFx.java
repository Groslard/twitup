package com.iup.tp.twitup.ihm.contents;

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
		
//		this.setBackground(Color.WHITE);
//		this.setLayout(new GridBagLayout());
		
		TextArea editorPane = new TextArea();
		this.add(editorPane, 0, 0);
		editorPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
//		editorPane.setLineWrap(true);
//		editorPane.setRows(50);
//		this.add(editorPane, new GridBagConstraints(0, 0, 1, 1, 0, 1, 
//				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
//				new Insets(5, 2, 2, 2), 0, 0));
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
