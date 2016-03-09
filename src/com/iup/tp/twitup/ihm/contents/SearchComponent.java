package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SearchComponent extends JPanel {
	public SearchComponent(){
		super();
		this.setBackground(Color.WHITE);
		this.add(new JButton("RECHERCHE"));
	}
}
