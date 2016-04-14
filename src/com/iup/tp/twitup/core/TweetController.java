package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ITwitListObserver;

public class TweetController implements IDatabaseObserver {
	/**
	 * BDD
	 */
	protected IDatabase database;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	protected final Set<ITwitListObserver> mObservers = new HashSet<ITwitListObserver>();

	protected ArrayList<Twit> tweets = new ArrayList<Twit>();

	protected SharedService shared;

	public TweetController(IDatabase mDatabase, EntityManager mEntityManager, SharedService shared) {
		this.mEntityManager = mEntityManager;
		this.database = mDatabase;
		this.database.addObserver(this);
		this.shared = shared;
	}

	/**
	 * @param text
	 */
	public void searchTwits(String text) {
		boolean isTagSearch = false;
		boolean isUserSearch = false;

		if (text.startsWith("#")) {
			isTagSearch = true;
			text = text.substring(1);
		} else if (text.startsWith("@")) {
			isUserSearch = true;
			text = text.substring(1);
		} else {
			isTagSearch = true;
			isUserSearch = true;
		}

		ArrayList<Twit> newTwitList = new ArrayList<Twit>();

		User user = this.shared.getConnectedUser();

		// Récupération des twits à filtrer
		Set<Twit> databaseTwits = this.database.getTwits();
		if (text == null || text.isEmpty()) {
			for (Twit twit : databaseTwits) {
				if (user.isFollowing(twit.getTwiter()) || twit.getText().contains("@" + user.getUserTag())) {
					newTwitList.add(twit);
				}
			}
		} else {
			if (isTagSearch) {
				for (Twit twit : databaseTwits) {
					if (!newTwitList.contains(twit) && twit.getText().contains("#" + text)) {
						newTwitList.add(twit);
					}
				}
			}

			if (isUserSearch) {
				for (Twit twit : databaseTwits) {
					if (!newTwitList.contains(twit)
							&& (twit.getTwiter().getUserTag().equals(text) || twit.getText().contains("@" + text))) {
						newTwitList.add(twit);
					}
				}
			}

		}

		// Ajout de la nouvelle liste
		this.tweets = newTwitList;
		this.sortTweets();
		this.notifyObservers();
	}

	private void sortTweets() {
		this.tweets.sort(new Comparator<Twit>() {
			@Override
			public int compare(Twit o1, Twit o2) {
				return (int) (o2.getEmissionDate() - o1.getEmissionDate());
			}
		});
	}

	public void addTweet(String content) {
		Twit twit = new Twit(shared.getConnectedUser(), content);
		this.mEntityManager.sendTwit(twit);
	}

	public void addObserver(ITwitListObserver observer) {
		this.mObservers.add(observer);
	}

	private void notifyObservers() {
		for (ITwitListObserver observer : mObservers) {
			observer.notifyTwitListHasChanged(tweets);
		}
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		tweets.add(addedTwit);
		this.sortTweets();
		notifyObservers();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		tweets.remove(deletedTwit);
		this.notifyObservers();
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