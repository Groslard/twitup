package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.datamodel.Twit;

public class TweetsQueueComponent extends JScrollPane {

	protected Set<TweetComponent> tweetsComponents; 
	
	protected JPanel content;
	
	public TweetsQueueComponent(){
		this.tweetsComponents = new HashSet<TweetComponent>();
		this.content = new JPanel();
		this.content.setBackground(Color.WHITE);
		this.content.setLayout(new GridBagLayout());
		
		
		this.setViewportView(this.content);
		//this.add(this.content);
		
		
		
	}
	
	
	public void notifyTwitsUpdated(Set<Twit> tweets){
		this.content.removeAll();
		this.tweetsComponents.clear();
		
		int line = 0;
		Iterator<Twit> iterator = tweets.iterator();
		
		while(iterator.hasNext()){
			TweetComponent tweetComp = new TweetComponent(iterator.next());
			this.tweetsComponents.add(tweetComp);
			
			
			this.content.add(tweetComp, new GridBagConstraints(0, line, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
					new Insets(0, 70, 5, 70), 0, 0));
			line++;
		}
		ViewController.updatePan(content);
	}
}
