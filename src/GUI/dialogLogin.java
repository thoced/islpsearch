package GUI;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.ProviderNotFoundException;
import java.sql.SQLException;

import CODE.src.retroAnalyseCode.CConfig;
import CODE.src.retroAnalyseCode.PropertiesNotFoundException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.ImageIcon;

public class dialogLogin extends JDialog implements ActionListener {
	private JTextField tLogin;
	private JPasswordField tPassword;
	private JButton buttonAnnuler;
	private JButton buttonLogin;

	public dialogLogin(final Frame frame, String titre, boolean modal) {
		super(frame, titre, modal);
		
		this.setSize(460,240);
		
		this.setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.setActionCommand("ANNULER");
		buttonAnnuler.addActionListener(this);
		panelButton.add(buttonAnnuler);
		
		buttonLogin = new JButton("Login");
		buttonLogin.setActionCommand("LOGIN");
		buttonLogin.addActionListener(this);
		panelButton.add(buttonLogin);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(dialogLogin.class.getResource("/logo3.png")));
		panelLogin.add(labelLogo, "8, 4, center, fill");
		
		JLabel lblNewLabel = new JLabel("Login");
		panelLogin.add(lblNewLabel, "4, 8");
		
		tLogin = new JTextField();
		panelLogin.add(tLogin, "8, 8, fill, default");
		tLogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		panelLogin.add(lblNewLabel_1, "4, 10");
		
		tPassword = new JPasswordField();
		panelLogin.add(tPassword, "8, 10, fill, default");
		tPassword.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand())
		{
		case "ANNULER" : this.setVisible(false);break;
		
		case "LOGIN" : try {
				this.Login();
			} catch (IOException | PropertiesNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}this.setVisible(false);
			break;
		
		default : break;
		}
		
	}
	
	public void Login() throws IOException, PropertiesNotFoundException
	{
		CConfig config = new CConfig();
		config.setLogin(tLogin.getText());
		config.setPassword(tPassword.getText());

		
	}
}
