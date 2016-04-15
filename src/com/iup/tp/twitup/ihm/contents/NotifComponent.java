package com.iup.tp.twitup.ihm.contents;
import java.util.Stack;

import com.iup.tp.twitup.core.TweetController;
import com.iup.tp.twitup.core.ViewControllerJfx;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class NotifComponent extends GridPane {
	Stack<Twit> twitToNotify = new Stack<Twit>();
	ViewControllerJfx viewController;
	
	
	
	public NotifComponent(ViewControllerJfx viewController){
		super();
		this.viewController = viewController;
	}
	
	public void notifyNewTwit(Twit newTwit){
		this.twitToNotify.push(newTwit);
		if(twitToNotify.size()==1){
			viewController.changeBotPanel(this);
			this.showNotif(newTwit);
		}
	}
	
	private void showNotif(Twit newTwit) {
		this.updateTwit(newTwit);
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Duration.millis(7000);
				if(twitToNotify.size()>0){
					showNotif(twitToNotify.pop());
				}else{
					viewController.changeBotPanel(null);;
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();
	}

	private void updateTwit(Twit newTwit) {
		this.getChildren().clear();
		this.getChildren().add(new TweetComponentFx(newTwit));
	}
}
