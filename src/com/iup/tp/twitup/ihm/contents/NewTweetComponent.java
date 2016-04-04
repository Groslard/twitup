package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iup.tp.twitup.core.TweetController;
import com.iup.tp.twitup.datamodel.User;

public class NewTweetComponent extends JPanel {

	private TweetController mTweetController;
	private JButton send;

	public NewTweetComponent(User connectedUser, TweetController mTweetController){
		super();
		
		this.mTweetController = mTweetController;
		
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		
		JTextArea editorPane = new JTextArea();
		editorPane.setLineWrap(true);
		editorPane.setRows(50);
		this.add(editorPane, new GridBagConstraints(0, 0, 1, 1, 0, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 2, 2, 2), 0, 0));
		send = new JButton("Envoyer Tweet");
		this.add(send, new GridBagConstraints(0, 1, 1, 1, 0, 0, 
				GridBagConstraints.SOUTH, GridBagConstraints.NONE, 
				new Insets(5, 2, 2, 2), 0, 0));
		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewTweetComponent.this.mTweetController.addTweet(editorPane.getText());
				editorPane.setText("");
				
			}
		});
	}
}
