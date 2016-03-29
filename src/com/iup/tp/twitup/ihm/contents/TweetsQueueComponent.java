package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.datamodel.Twit;

public class TweetsQueueComponent extends JScrollPane {

	protected HashMap<Twit, TweetComponent> tweetsComponents; 
	
	protected JPanel content;
	
	protected GridBagConstraints tweetPlacement;

	private GridBagLayout gridBagLayout;
	
	public TweetsQueueComponent(){
		this.tweetsComponents = new HashMap<Twit, TweetComponent>();
		this.content = new JPanel();
		this.content.setBackground(Color.WHITE);
		gridBagLayout = new GridBagLayout();
		this.content.setLayout(gridBagLayout);
		
		this.setViewportView(this.content);
		
		this.tweetPlacement = new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 70, 5, 70), 0, 0);
		
	}
	
	
	public void notifyTwitsUpdated(ArrayList<Twit> tweets){
		int line = 0;
		Iterator<Twit> iterator = tweets.iterator();
		
		while(iterator.hasNext()){
			this.tweetPlacement.gridy = line;
			Twit twit = iterator.next();
			
			TweetComponent tweetComp = this.tweetsComponents.get(twit);
			
			if(tweetComp == null){
				tweetComp = new TweetComponent(twit);
				this.tweetsComponents.put(twit, tweetComp);
				this.content.add(tweetComp, this.tweetPlacement);
			}else{
				// TODO : mettre a jour la constraint sur le comp si il existe
				gridBagLayout.setConstraints(tweetComp, this.tweetPlacement);
			}
			
			
			line++;
		}
		ViewController.updatePan(content);
	}
	
	public void notifyTwitUpdated(Twit tweet){
		// TODO : faire une fonction qui permet de creer un tweetcomponent a partir de tweet,
		// Qui va l'ajouter en haut et qui va mettre à jour les constraints des autres
//		this.content.getComponents()
//		this.content.add(comp, index)
	}
}
