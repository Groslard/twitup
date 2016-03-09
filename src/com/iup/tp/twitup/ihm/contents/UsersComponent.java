package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UsersComponent extends JPanel {

	public UsersComponent(){
		super();
		this.setBackground(Color.WHITE);
		this.add(new JButton("USER"));
	}
}
