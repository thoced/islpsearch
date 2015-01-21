package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;

public class panelLogo extends JPanel
{
	private InputStream stream;
	private Image ima;
	private JLabel labelImage;
	
	public panelLogo() throws IOException
	{
		//this.setSize(320,32);
		
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		
		labelImage = new JLabel("");

		this.add(labelImage);
		
		stream = panelLogo.class.getResourceAsStream("/logo3.png");
		
		 ima = ImageIO.read(stream);
		 
		 labelImage.setIcon(new ImageIcon(ima));
	
	}
	

	@Override
	protected void paintComponent(Graphics arg0) 
	{
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		
		Graphics2D graph = (Graphics2D)arg0;
		
	
		
		
	}

}
