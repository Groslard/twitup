package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NewTweetComponent extends JPanel {
	public NewTweetComponent(){
		super();
		this.setBackground(Color.WHITE);
		this.add(new JButton("NOUVEAU TWEET"));
	}
}
