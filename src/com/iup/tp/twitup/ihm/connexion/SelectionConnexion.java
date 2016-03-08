package com.iup.tp.twitup.ihm.connexion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.ihm.TwitupMainView;

public class SelectionConnexion extends JPanel {

	protected JButton bConnexion;
	protected JButton bInscription;

	public SelectionConnexion() {
		super();

	}

	public void showGUI() {
		if (bConnexion == null) {
			this.initGUI();
		}
		
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage

				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				bInscription.setSize(screenSize.width / 8, screenSize.height / 8);
				bConnexion.setSize(screenSize.width / 8, screenSize.height / 8);
				
				// Affichage

			}
		});
		
		this.setVisible(true);
	}

	protected void initGUI() {

		this.setLayout(new GridBagLayout());
		this.bConnexion = new JButton("Connexion");
		this.bInscription = new JButton("Inscription");
		this.bConnexion.setPreferredSize(new Dimension(100, 50));
		this.bInscription.setPreferredSize(new Dimension(100, 50));

		this.bInscription.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		this.bConnexion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		this.add(bInscription, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		this.add(bConnexion, new GridBagConstraints(0, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						5, 5, 5, 5), 0, 0));
		

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(250, 150);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		SelectionConnexion selec = new SelectionConnexion();
		selec.showGUI();
		f.add(selec);
		f.pack();
	}
}
