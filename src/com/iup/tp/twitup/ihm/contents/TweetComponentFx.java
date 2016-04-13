package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class TweetComponentFx extends GridPane {
	static String dateFormat = "dd/MM/yy HH:mm:ss";
	static String defaultIcon = "./src/resources/images/lamaIcon.png";
	
	public TweetComponentFx(Twit twit){
		super();
//		this.setBackground(new Background()));
//		this.setBackground(Color.WHITE);
//		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

		File img = new File(twit.getTwiter().getAvatarPath());
		
		if(!img.exists() || img.isDirectory())
			img = new File(defaultIcon);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(img);
			// TODO Ressortir le resize img dans un environnement plus global et réutilisable
	//		ImageIcon resizedImg = new ImageIcon(myPicture.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//			Label picLabel = new Label(resizedImg);
//			
//			Image pic = new Image(getClass().getResourceAsStream("roses.jpg"));
//			Pane p = new Pane();
//			p.setOpaque(false);
//			p.add(picLabel);
//			this.add(p, new GridBagConstraints(0, 0, 1, 1, 0, 0, 
//					GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
//					new Insets(5, 2, 2, 2), 0, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel msgPan = new JPanel();
		msgPan.setLayout(new GridBagLayout());
		msgPan.setOpaque(false);
		
		msgPan.add(new JLabel(twit.getTwiter().getName()), new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
		msgPan.add(new JLabel("@"+twit.getTwiter().getUserTag()), new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
        Date date=new Date(twit.getEmissionDate());
        SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
        String dateText = df2.format(date);
        
        msgPan.add(new JLabel(dateText), new GridBagConstraints(1, 0, 1, 2, 1, 1, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
        
        msgPan.add(new JLabel(twit.getText()), new GridBagConstraints(0, 2, 2, 1, 1, 1, 
        		GridBagConstraints.WEST, GridBagConstraints.NONE, 
        		new Insets(15, 2, 15, 2), 0, 0));
        
//        this.add(msgPan,new GridBagConstraints(1, 0, 1, 1, 1, 1, 
//				GridBagConstraints.WEST
//				, GridBagConstraints.BOTH, 
//				new Insets(2, 2, 2, 2), 0, 0));
        
		
		
	}
	
	
}
