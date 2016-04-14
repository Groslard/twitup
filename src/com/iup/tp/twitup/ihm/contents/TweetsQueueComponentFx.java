package com.iup.tp.twitup.ihm.contents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.ViewController;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TweetsQueueComponentFx extends ScrollPane {

	protected HashMap<Twit, TweetComponent> tweetsComponents; 
	
	protected GridPane content;
	
	protected GridBagConstraints tweetPlacement;

	private GridBagLayout gridBagLayout;
	
	public TweetsQueueComponentFx(){
		this.tweetsComponents = new HashMap<Twit, TweetComponent>();
		
		
		
		this.content = new GridPane();
//		this.content.setBackground(Color.WHITE);
		gridBagLayout = new GridBagLayout();
//		this.content.setLayout(gridBagLayout);
		
		this.setContent(this.content);
//		this.getVerticalScrollBar().setUnitIncrement(10);
//		this.tweetPlacement = new GridBagConstraints(0, 0, 1, 1, 1, 1, 
//				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
//				new Insets(0, 70, 5, 70), 0, 0);
//		
//		
//		GridPane.setRowIndex(nw, y);
//		content.setr
	}
	
	
	public void notifyTwitsUpdated(ArrayList<Twit> tweets){
//		Set<Twit> temp = tweetsComponents.keySet();
//		temp.removeAll(tweets);
//		for (Twit twit : temp) {
//			TweetComponent twitComponent = this.tweetsComponents.get(twit);
//			this.content.remove(twitComponent);
//			this.tweetsComponents.remove(twit);
//		}
		
		int line = 0;
		Iterator<Twit> iterator = tweets.iterator();
		
		while(iterator.hasNext()){
			Twit twit = iterator.next();
			
			TweetComponent tweetComp = this.tweetsComponents.get(twit);
			
			if(tweetComp == null){
				tweetComp = new TweetComponent(twit);
				this.tweetsComponents.put(twit, tweetComp);
				//this.content.add(tweetComp, 0, line);
			}else{
				//GridPane.setRowIndex(tweetComp, line);
				// TODO : mettre a jour la constraint sur le comp si il existe
//				gridBagLayout.setConstraints(tweetComp, this.tweetPlacement);
			}
			
			
			line++;
		}
//		ViewController.updatePan(content);
	}
	
}
