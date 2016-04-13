package com.iup.tp.twitup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import com.iup.tp.twitup.core.Twitup;
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
	        primaryStage.setTitle("JavaFX Welcome");
	        TwitupFx twitup = new TwitupFx(primaryStage);
	        twitup.initControllers();
	        primaryStage.show();
	    }
 

    
    
    

}
