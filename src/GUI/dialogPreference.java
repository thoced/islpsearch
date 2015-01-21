package GUI;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import CODE.src.retroAnalyseCode.CConfig;
import CODE.src.retroAnalyseCode.PropertiesNotFoundException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dialogPreference extends JDialog 
{
	private JTextField tDriver;
	private JTextField tLogin;
	private JTextField tPassword;
	private JTextField tUrl;
	
	public void save()
	{
		try 
		{
			CConfig config = new CConfig();
			
			config.setLogin(tLogin.getText());
			config.setPassword(tPassword.getText());
			config.setDriver(tDriver.getText());
			config.setUrl(tUrl.getText());
			
			config.saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertiesNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public dialogPreference(Frame frame,String titre,boolean modal)
	{
		
		
		
		super(frame,titre,modal);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				try
				{
					CConfig config = new CConfig();
					
					tLogin.setText(config.getLogin());
					tPassword.setText(config.getPassword());
					tDriver.setText(config.getDriver());
					tUrl.setText(config.getUrl());
					
					
				} catch (IOException | PropertiesNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.setSize(640, 359);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLocationRelativeTo(frame);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("driver server (SQL)");
		lblNewLabel.setBounds(26, 39, 179, 15);
		panel.add(lblNewLabel);
		
		tDriver = new JTextField();
		tDriver.setBounds(224, 37, 179, 19);
		panel.add(tDriver);
		tDriver.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Login :");
		lblNewLabel_1.setBounds(26, 140, 179, 15);
		panel.add(lblNewLabel_1);
		
		tLogin = new JTextField();
		tLogin.setBounds(224, 138, 179, 19);
		panel.add(tLogin);
		tLogin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setBounds(26, 198, 179, 15);
		panel.add(lblNewLabel_2);
		
		tPassword = new JTextField();
		tPassword.setBounds(224, 196, 179, 19);
		panel.add(tPassword);
		tPassword.setColumns(10);
		
		JButton buttonAppliquer = new JButton("Appliquer");
		buttonAppliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				save();
				
			}
		});
		buttonAppliquer.setBackground(Color.GREEN);
		buttonAppliquer.setBounds(22, 272, 117, 25);
		panel.add(buttonAppliquer);
		
		JLabel lblUrlDatabase = new JLabel("url database");
		lblUrlDatabase.setBounds(26, 94, 179, 15);
		panel.add(lblUrlDatabase);
		
		tUrl = new JTextField();
		tUrl.setColumns(10);
		tUrl.setBounds(224, 92, 179, 19);
		panel.add(tUrl);
		
		this.setVisible(true);
	}
}
