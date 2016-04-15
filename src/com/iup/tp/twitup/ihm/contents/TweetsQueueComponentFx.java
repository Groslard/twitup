package com.iup.tp.twitup.ihm.contents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.ITwitListObserver;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TweetsQueueComponentFx extends ScrollPane implements ITwitListObserver {

	protected Map<Twit, TweetComponentFx> twitMap = new TreeMap<Twit, TweetComponentFx>(new Comparator<Twit>() {
		@Override
		public int compare(Twit o1, Twit o2) {
			return (int) (o2.getEmissionDate() - o1.getEmissionDate());
		}
	});

	protected GridPane contentPane = new GridPane();
	protected SearchComponent searchComponent = new SearchComponent();
	protected GridPane twitsPane = new GridPane();

	public TweetsQueueComponentFx() {
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	
	
		this.setContent(this.contentPane);
		this.contentPane.setVgap(5);
		GridPane.setMargin(searchComponent, new Insets(5,5,5,40));
		this.contentPane.add(searchComponent, 0, 0);
		this.contentPane.add(twitsPane, 0, 1);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	}

	public SearchComponent getSearchComponent() {
		return searchComponent;
	}

	@Override
	public synchronized void notifyTwitListHasChanged(List<Twit> twits) {
		List<TweetComponentFx> newTwits = new ArrayList<TweetComponentFx>();
		for (Twit twit : twits) {

			TweetComponentFx component = twitMap.get(twit);

			// Nouveau twit
			if (component == null) {
				TweetComponentFx newTwitComponent = this.createTwitComponent(twit);
				this.addTwitComponent(twit, newTwitComponent);
				newTwits.add(newTwitComponent);
			}
		}

		List<TweetComponentFx> deletedTwits = new ArrayList<TweetComponentFx>();
		List<Twit> toRemove = new ArrayList<Twit>();
		for (Twit oldTwit : twitMap.keySet()) {
			if (twits.contains(oldTwit) == false) {
				TweetComponentFx oldTwitComponent = twitMap.get(oldTwit);
				if (oldTwitComponent != null) {
					deletedTwits.add(oldTwitComponent);
				}
				toRemove.add(oldTwit);
			}
		}
		for (Twit remove : toRemove) {
			twitMap.remove(remove);
		}

		Runnable r = new Runnable() {

			@Override
			public void run() {
				updateTwitsComponents(deletedTwits, newTwits);
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

	protected void updateTwitsComponents(List<TweetComponentFx> deletedTwits, List<TweetComponentFx> newTwits) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (TweetComponentFx oldTwitComponent : deletedTwits) {
					oldTwitComponent.hideTwit();
				}

				replaceTwit(new ArrayList<Twit>(twitMap.keySet()));

				for (TweetComponentFx newTwitComponent : newTwits) {
					newTwitComponent.showTwit();
				}
			}
		});
	}

	private void replaceTwit(List<Twit> twits) {

		int posY = 1;

		for (Twit twit : twits) {
			TweetComponentFx component = twitMap.get(twit);

			if (component != null) {
				GridPane.setConstraints(component, 0, posY);
				posY++;
			}
		}
	}

	protected void addTwitComponent(Twit twit, TweetComponentFx component) {
		twitMap.put(twit, component);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				GridPane.setMargin(component, new Insets(5,5,5,40));
				twitsPane.add(component, 0, 0);
				GridPane.setFillWidth(component, true);

				GridPane.setHalignment(component, HPos.CENTER);
			}
		});
	}

	protected TweetComponentFx createTwitComponent(Twit twit) {
		TweetComponentFx mockTwitComponent = new TweetComponentFx(twit);
		mockTwitComponent.setVisible(false);

		return mockTwitComponent;
	}

}
