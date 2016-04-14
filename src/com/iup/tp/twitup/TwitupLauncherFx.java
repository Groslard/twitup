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
 
//exemple qui marche 
    
//	 @Override
//	    public void start(Stage primaryStage) {
//	        primaryStage.setTitle("twiup");
//	        GridPane grid = new GridPane();
//	        grid.setAlignment(Pos.CENTER);
//	        grid.setHgap(10);
//	        grid.setVgap(10);
//	        grid.setPadding(new Insets(25, 25, 25, 25));
//
//	        Text scenetitle = new Text("Welcome");
//	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//	        grid.add(scenetitle, 0, 0, 2, 1);
//
//	        Label userName = new Label("User Name:");
//	        grid.add(userName, 0, 1);
//
//	        TextField userTextField = new TextField();
//	        grid.add(userTextField, 1, 1);
//
//	        Label pw = new Label("Password:");
//	        grid.add(pw, 0, 2);
//
//	        PasswordField pwBox = new PasswordField();
//	        grid.add(pwBox, 1, 2);
//
//	        Button btn = new Button("Sign in");
//	        HBox hbBtn = new HBox(10);
//	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//	        hbBtn.getChildren().add(btn);
//	        grid.add(hbBtn, 1, 4);
//
//	        final Text actiontarget = new Text();
//	        grid.add(actiontarget, 0, 6);
//	        grid.setColumnSpan(actiontarget, 2);
//	       // grid.setHalignment(actiontarget, RIGHT);
//	        actiontarget.setId("actiontarget");
//
//	        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//	            @Override
//	            public void handle(ActionEvent e) {
//	                actiontarget.setFill(Color.FIREBRICK);
//	                actiontarget.setText("Sign in button pressed");
//	            }
//	        });
//
//	        Scene scene = new Scene(grid, 300, 275);
//	        primaryStage.setScene(scene);
//	        primaryStage.show();
//	    }
    

}
