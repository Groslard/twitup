package com.iup.tp.twitup.ihm.contents;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.ihm.IUserSearchObserver;

public class SearchUserComponent extends GridPane {

	protected final Set<IUserSearchObserver> mObservers = new HashSet<IUserSearchObserver>();
	
	protected TextField searchTextField;


	public SearchUserComponent() {

		searchTextField = new javafx.scene.control.TextField();

		this.add(searchTextField , 0 , 0);
		GridPane.setFillWidth(searchTextField, true);
		GridPane.setHgrow(searchTextField, Priority.ALWAYS);
		
		searchTextField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            SearchUserComponent.this.notifyObservers(newValue);
	        }
	    });
	}


	public void addObserver(IUserSearchObserver observer) {
		this.mObservers.add(observer);
	}
	
	private void notifyObservers(String text) {
		for (IUserSearchObserver observer : mObservers) {
			observer.notifyUserSearched(text);
		}
	}
}
