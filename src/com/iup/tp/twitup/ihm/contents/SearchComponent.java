package com.iup.tp.twitup.ihm.contents;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.ihm.ITwitSearchObserver;

public class SearchComponent extends GridPane {

	protected final Set<ITwitSearchObserver> mObservers = new HashSet<ITwitSearchObserver>();
	
	protected TextField searchTextField;


	public SearchComponent() {

		searchTextField = new javafx.scene.control.TextField();
		this.add(searchTextField , 0 , 0);
		GridPane.setFillWidth(searchTextField, true);
		GridPane.setHgrow(searchTextField, Priority.ALWAYS);
		
		searchTextField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            SearchComponent.this.notifyObservers(newValue);
	        }
	    });
	}


	public void addObserver(ITwitSearchObserver observer) {
		this.mObservers.add(observer);
	}
	
	private void notifyObservers(String text) {
		for (ITwitSearchObserver observer : mObservers) {
			observer.notifyTwitSearched(text);
		}
	}
}
