package com.iup.tp.twitup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import com.iup.tp.twitup.core.Twitup;

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
    public void start(Stage stage) throws Exception {

        
        Twitup twitup = new Twitup();
        twitup.initControllers();
		twitup.initGui(stage);
        
    }
    
    

}
