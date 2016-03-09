package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TweetsComponent extends JPanel {

	public TweetsComponent(){
		super();
		this.setBackground(Color.WHITE);
		this.add(new JButton("ACCUEIL"));
	}
}
