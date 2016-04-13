package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.UserController;
import com.iup.tp.twitup.datamodel.User;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;




public class UsersComponentFx extends GridPane {
	static String defaultIcon = "./src/resources/images/lamaIcon.png";

	protected UserController userController;
	protected User user;
	protected JButton buttonFollow;
	public UsersComponentFx(User user,UserController userController){
		super();
		this.userController=userController;
		this.user=user;
//		this.setBackground(Color.WHITE);
//		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
//		this.setLayout(new GridBagLayout());
		this.buttonFollow=new JButton("follow");
		
		
		File img = new File(this.user.getAvatarPath());
		
		if(!img.exists() || img.isDirectory())
			img = new File(defaultIcon);
		
		BufferedImage myPicture;
		try {
			
			myPicture = ImageIO.read(img);
			// TODO Ressortir le resize img dans un environnement plus global et réutilisable
			ImageIcon resizedImg = new ImageIcon(myPicture.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//			JLabel picLabel = new JLabel(resizedImg);
//			this.add(picLabel, new GridBagConstraints(0, 0, 1, 2, 1, 1, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE, 
//					new Insets(5, 2, 2, 2), 0, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		
//		this.add(new JLabel(this.user.getName()), new GridBagConstraints(1, 0, 1, 1, 1, 1, 
//				GridBagConstraints.WEST, GridBagConstraints.NONE, 
//				new Insets(2, 2, 2, 2), 0, 0));
//		
//		this.add(new JLabel("@"+this.user.getUserTag()), new GridBagConstraints(1, 1, 1, 1, 1, 1, 
//				GridBagConstraints.WEST, GridBagConstraints.NONE, 
//				new Insets(2, 2, 2, 2), 0, 0));
//		
//		this.add(buttonFollow, new GridBagConstraints(2, 0, 1, 2, 1, 1, 
//				GridBagConstraints.EAST, GridBagConstraints.NONE, 
//				new Insets(2, 2, 2, 2), 0, 0));
		
		this.buttonFollow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				userController.followUser(user);
			}
		});		
		
	}
}
