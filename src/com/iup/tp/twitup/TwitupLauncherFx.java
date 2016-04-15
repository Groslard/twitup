package com.iup.tp.twitup;

import javafx.application.Application;
import javafx.stage.Stage;


import com.iup.tp.twitup.core.TwitupFx;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncherFx extends Application
{

	 public static void main(String[] args) {
        launch(args);
    }
	 @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("TwitUp");
	        TwitupFx twitup = new TwitupFx(primaryStage);
	        twitup.initControllers();
	        twitup.initGui();
	        primaryStage.show();
	    }
 


}
