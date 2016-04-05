package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.core.viewControllers.TweetsQueuController;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TweetController implements IDatabaseObserver{
	/**
	 * BDD
	 */
	protected IDatabase mDatabase;
	
	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;
	
	protected Set<Twit> _tweets;
	
	protected ArrayList<Twit> tweets;
	
	/**
	 * Tweets views
	 */
	protected TweetsQueuController tweetsQueu;
	
	protected SharedService sharedService;

	public TweetController(IDatabase mDatabase, EntityManager mEntityManager, TweetsQueuController tweetsQueu, SharedService sharedService) {
		super();
		this.mEntityManager = mEntityManager;
		this.mDatabase = mDatabase;
		this.tweetsQueu = tweetsQueu;
		
		this.tweets = new ArrayList<Twit>();
		
		this.mDatabase.addObserver(this);
		
		this.sharedService = sharedService;
	}

	public void loadTweetsFromDb(){
		this.tweets.clear();
		this.tweets.addAll(mDatabase.getTwits());
		sortTweets();
		tweetsQueu.notifyTwitsUpdated(this.tweets);
	}

	private void sortTweets() {
		this.tweets.sort(new Comparator<Twit>() {
			@Override
			public int compare(Twit o1, Twit o2) {
				return (int) (o2.getEmissionDate() - o1.getEmissionDate());
			}
		});
	}
	
	public void showTweets(){
		this.sortTweets();
		tweetsQueu.notifyTwitsUpdated(this.tweets);
	}

	public void addTweet(String content){
		Twit twit = new Twit(sharedService.getConnectedUser(), content);
		this.mEntityManager.sendTwit(twit);
	}
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		this.tweets.add(addedTwit);
		this.showTweets();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
	
	
}
