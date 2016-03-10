package com.iup.tp.twitup.ihm.contents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.iup.tp.twitup.datamodel.Twit;

public class TweetComponent extends JPanel {
	static String dateFormat = "dd/MM/yy HH:mm:ss";
	static String defaultIcon = "./src/resources/images/lamaIcon.png";
	
	public TweetComponent(Twit twit){
		super();
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		this.setLayout(new GridBagLayout());
		
		System.out.println(twit.getTwiter().getAvatarPath());
		
		
		File img = new File(twit.getTwiter().getAvatarPath());
		
		if(!img.exists() || img.isDirectory())
			img = new File(defaultIcon);
		
		BufferedImage myPicture;
		try {
			
			myPicture = ImageIO.read(img);
			// TODO Ressortir le resize img dans un environnement plus global et réutilisable
			ImageIcon resizedImg = new ImageIcon(myPicture.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			JLabel picLabel = new JLabel(resizedImg);
			this.add(picLabel, new GridBagConstraints(0, 0, 1, 2, 1, 1, 
					GridBagConstraints.WEST, GridBagConstraints.NONE, 
					new Insets(5, 2, 2, 2), 0, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		this.add(new JLabel(twit.getTwiter().getName()), new GridBagConstraints(1, 0, 1, 1, 1, 1, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
		this.add(new JLabel("@"+twit.getTwiter().getUserTag()), new GridBagConstraints(1, 1, 1, 1, 1, 1, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
        Date date=new Date(twit.getEmissionDate());
        SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
        String dateText = df2.format(date);
		
		this.add(new JLabel(dateText), new GridBagConstraints(2, 0, 1, 2, 1, 1, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 0, 0));
		
		this.add(new JLabel(twit.getText()), new GridBagConstraints(1, 2, 3, 1, 1, 1, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, 
				new Insets(15, 2, 15, 2), 0, 0));
		
	}
	
	
}
