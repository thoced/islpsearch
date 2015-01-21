package GUI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class dialogHelp extends JDialog 
{
	private InputStream stream;
	private Image logo;
	private JLabel labelLogo;
	
	public dialogHelp(Frame frame,String titre,boolean modal) 
	{
		super(frame,titre,modal);
		
		super.setSize(600, 480);
		
		super.setLocationRelativeTo(frame);
		
		stream = dialogHelp.class.getResourceAsStream("/logo3.png");
	
		
		
		
		this.getContentPane().setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		getContentPane().setLayout(null);
		
		JLabel lblProgrammerCdric = new JLabel("Propriété du logiciel : Cédric Thonon");
		lblProgrammerCdric.setForeground(Color.ORANGE);
		lblProgrammerCdric.setBounds(42, 79, 373, 15);
		getContentPane().add(lblProgrammerCdric);
		
		JLabel label = new JLabel("Programmeur : Cédric Thonon");
		label.setIcon(null);
		label.setForeground(Color.ORANGE);
		label.setBounds(42, 166, 288, 15);
		getContentPane().add(label);
		
		JLabel lblIslpSearch = new JLabel("ISLP Search ©  (Copyright déposé)");
		lblIslpSearch.setForeground(Color.ORANGE);
		lblIslpSearch.setBounds(42, 52, 325, 15);
		getContentPane().add(lblIslpSearch);
		
		JLabel lblDbMysql = new JLabel("DB : Mysql");
		lblDbMysql.setForeground(Color.ORANGE);
		lblDbMysql.setBounds(42, 193, 201, 15);
		getContentPane().add(lblDbMysql);
		
		JLabel lblIslpSearchVersion = new JLabel("ISLP Search  Version 0.8  (Version Multi-utilisateurs 21/01/2015)");
		lblIslpSearchVersion.setForeground(Color.ORANGE);
		lblIslpSearchVersion.setBounds(42, 124, 517, 15);
		getContentPane().add(lblIslpSearchVersion);
		
		labelLogo = new JLabel("");
		labelLogo.setBounds(270, 386, 316, 53);
		getContentPane().add(labelLogo);
		
		try {
			logo = ImageIO.read(stream);
			labelLogo.setIcon(new ImageIcon(logo));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.setVisible(true);
	}
}
