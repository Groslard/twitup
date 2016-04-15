package com.iup.tp.twitup.ihm;


import java.io.File;

import javax.swing.JFileChooser;

import com.iup.tp.twitup.core.ViewControllerJfx;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainViewFx implements IDatabaseObserver {
	private ViewControllerJfx viewController;

	protected BorderPane root;

	/** Menu Bar **/
	protected GridPane leftPan;
	protected GridPane centerPan;
	protected GridPane rightPan;

	protected Stage stage;



	public TwitupMainViewFx(ViewControllerJfx viewController) {
		this.viewController = viewController;
		this.stage = this.viewController.getStage();
		
		this.root = new BorderPane();
		this.root.setStyle("-fx-background-color: #F5F8FA");
	}

	public void showGUI() {

		final Scene scene = new Scene(root, 600, 400);
		this.stage.setScene(scene);
	}

	public void setLeftPan(Node newLeftPan) {
		this.root.setLeft(newLeftPan);
	}

	public void setCenterPan(Node component) {
		this.root.setCenter(component);
	}

	public void setRightPan(Node newRightPan) {
		this.root.setRight(newRightPan);
	}

	public File showGuiDirPath() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(fc);
		return fc.getSelectedFile();
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
