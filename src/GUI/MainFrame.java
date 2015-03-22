package GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import CODE.src.retroAnalyseCode.CConfig;
import CODE.src.retroAnalyseCode.CImportFile;
import CODE.src.retroAnalyseCode.CTransaction;
import CODE.src.retroAnalyseCode.DebugScript;
import CODE.src.retroAnalyseCode.DossierConnection;
import CODE.src.retroAnalyseCode.PropertiesNotFoundException;
import CODE.src.retroAnalyseCode.threadImport;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.event.WindowFocusListener;
import java.awt.Color;

import javax.swing.UIManager;

public class MainFrame {

	private JFrame frmIslpSearch;
	private JLabel labelNbEnregistrement;

	
	public void RechercheTuples()
	{
		// affichage du nombre d'enregistrement
		CTransaction transaction = new CTransaction();
		try 
		{
			long nbEnregistrement = transaction.getNombreTupleIslp();
			// reception de la dernière update
			labelNbEnregistrement.setText(String.format("%d enregistrement(s) ISLP actuels dans la base", nbEnregistrement));
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			labelNbEnregistrement.setText("Erreur Recherche Nombre Enregistrement");
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmIslpSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIslpSearch = new JFrame();
		frmIslpSearch.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmIslpSearch.setBackground(Color.LIGHT_GRAY);
		frmIslpSearch.setTitle("ISLP Search  Version 0.9  (Version Multi-utilisateurs 22/03/2015)");
		frmIslpSearch.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0)
			{
				// affichage du nombre d'enregistrement
				RechercheTuples();
			}
			public void windowLostFocus(WindowEvent arg0)
			{
			}
		});
		frmIslpSearch.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				
				// ouverture dialoglogin
				/*dialogLogin dia = new dialogLogin(null,"Login",true);
				dia.setVisible(true);
				
				// Config
				
					try {
						CConfig config = new CConfig();
					} catch (IOException | PropertiesNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmIslpSearch, "Le fichier de configuration n'existe pas, il vient d'être créer ou une erreur est survenue lors de la création de celui-ci, "
								+ "la boite de dialogue des préférences va s'ouvrir");
						
						dialogPreference dp = new dialogPreference(frmIslpSearch,"Préférences",true);
						e1.printStackTrace();
					}
					
				*/
				
				// ouverture de l'application et lancement du script sql
				DossierConnection.ExecuteScript();
				
				
				
				
			}
		});
		frmIslpSearch.setSize(1024, 768);
		frmIslpSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmIslpSearch.setJMenuBar(menuBar);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		
		JMenuItem mQuitter = new JMenuItem("Quitter");
		mQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				frmIslpSearch.dispose();
			}
		});
		
		JMenuItem mPreference = new JMenuItem("Preferences");
		mPreference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dialogPreference dp = new dialogPreference(frmIslpSearch,"Preferences",true);
			}
		});
		menuFichier.add(mPreference);
		menuFichier.add(mQuitter);
		
		JMenu menuOption = new JMenu("Option ISLP");
		menuBar.add(menuOption);
		
		JMenuItem mImportation = new JMenuItem("Importer des donnees ISLP");
		mImportation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(true);
				int result = jfc.showOpenDialog(frmIslpSearch);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String t = String.format("Etes-vous sûr de vouloir importer les données de %d fichier(s)", jfc.getSelectedFiles().length);
					int res = JOptionPane.showConfirmDialog(frmIslpSearch,t,"Confirmation", 0);
					
					if(res == JOptionPane.OK_OPTION)
					{
						File[] file = jfc.getSelectedFiles();
						/*
						 * 
						 * 
						 */
						threadImport ti = new threadImport(file);
						dialogProgress dp = new dialogProgress(frmIslpSearch,"Progression",true);
						ti.setDialogProgress(dp);
						ti.start();
						dp.getProgressBarTotal().setMaximum(file.length);
						dp.getProgressBarTotal().setValue(0);
						dp.getProgressBarTotal().setStringPainted(true);
						dp.setVisible(true);
						// affichage du nouveau nombre de tuple dans la base
						
						
						
						// lancement de l'import
					//	CImportFile cif = new CImportFile();
						//cif.Import(file);
						
					}
				}
			}
		});
		menuOption.add(mImportation);
		
		JMenuItem mExport = new JMenuItem("Exporter la DB");
		mExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser jfc = new JFileChooser();
				int ret = jfc.showSaveDialog(frmIslpSearch);
				if(ret == JFileChooser.APPROVE_OPTION)
				{
					CTransaction transaction = new CTransaction();
					try
					{
						String records = transaction.getAllRecords();
						
						FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile());
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						bos.write(records.getBytes());
						
						bos.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
				
		});
		menuOption.add(mExport);
		
		JMenu menuRecherches = new JMenu("Recherches");
		menuBar.add(menuRecherches);
		
		JMenuItem mRechercheMultiples = new JMenuItem("Recherches multiples");
		mRechercheMultiples.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dialogRecherche dr = new dialogRecherche(frmIslpSearch,"Recherche",true);
			}
		});
		menuRecherches.add(mRechercheMultiples);
		
		JMenu menuHelp = new JMenu("?");
		menuBar.add(menuHelp);
		
		JMenuItem mAbout = new JMenuItem("A propos de");
		mAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dialogHelp dh = new dialogHelp(frmIslpSearch,"A propos de",true);
			}
		});
		menuHelp.add(mAbout);
		
		JMenu menuDebug = new JMenu("Debug");
		menuDebug.setEnabled(false);
		menuBar.add(menuDebug);
		
		JMenuItem mWriteFile = new JMenuItem("Ecriture fichier debug");
		mWriteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DebugScript ds = new DebugScript();
				ds.createDebugFile();
			}
		});
		menuDebug.add(mWriteFile);
		frmIslpSearch.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Information ISLP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmIslpSearch.getContentPane().add(panel,BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		labelNbEnregistrement = new JLabel("Nombre d'enregistrement ISLP:");
		panel.add(labelNbEnregistrement, BorderLayout.EAST);
		
		
	
		
		// panel logo
		try 
		{
			panelLogo pLogo = new panelLogo();
			frmIslpSearch.getContentPane().add(pLogo,BorderLayout.CENTER);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		frmIslpSearch.setExtendedState(frmIslpSearch.MAXIMIZED_BOTH); 
	
		
	}
}
