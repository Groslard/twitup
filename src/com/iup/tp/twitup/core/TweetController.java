package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TweetController implements IDatabaseObserver{
	/**
	 * BDD
	 */
	protected IDatabase mDatabase;
	
	protected Set<Twit> tweets;
	
	/**
	 * Tweets views
	 */
	protected ViewController mViewController;

	public TweetController(IDatabase mDatabase, ViewController mViewController) {
		super();
		this.mDatabase = mDatabase;
		this.mViewController = mViewController;
	}

	public void loadTweets(){
		this.tweets = mDatabase.getTwits();
		mViewController.getCompTweetsQueue().notifyTwitsUpdated(this.tweets);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
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
