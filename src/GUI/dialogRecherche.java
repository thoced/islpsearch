package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import CODE.src.retroAnalyseCode.CResultPrint;
import CODE.src.retroAnalyseCode.CTransaction;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import java.awt.Font;

import javax.swing.JEditorPane;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class dialogRecherche extends JDialog implements ActionListener,MouseListener
{
	private JButton buttonColler;
	private Popup popup;
	private JButton buttonSearch;
	private JTextArea textContact;
	private JButton buttonPrint;
	private JEditorPane textResult;
	
	private JPanel panelParametric;
	private JRadioButton radioFullNumero;
	private JRadioButton radioLeftNumero;
	private ButtonGroup groupRadio;
	private JComboBox comboNombre;
	
	private String[] backupResultat;
	private JLabel labelExemple;
	private JButton buttonNettoyer;
	
	private popupMenuColler popColler;
	
	public String search(String text)
	{
		String resultat = null;
		
		if(text.isEmpty())
		{
			resultat = "Veuillez inscrire au minimum un numero dans le champ de recherche";
			return resultat;
		}
		
		String[] listes = text.split("\n");
		
		CTransaction transaction = new CTransaction();
		try 
		{
			String[] ret = null;
			if(radioFullNumero.isSelected())
			{
				ret = transaction.search(listes,1,0);
			}
			if(radioLeftNumero.isSelected())
			{
				ret = transaction.search(listes, 2,Integer.parseInt((String) comboNombre.getSelectedItem()));
			}
			
			
			if(ret == null)return "Erreur transaction";
			
			if(ret.length > 0)
			{
				StringBuilder builder = new StringBuilder();
				
				// en tête html
				builder.append("<HTML><HEAD><TITLE>Résultats</TITLE></HEAD><BODY>"
						+ "<TABLE border='0' width='100%'>");
				
				backupResultat = ret;
				
				for(String r : ret)
				{
					String[] t = r.split("\t");
				
					String temp = String.format("<tr>"
			    			+ "<td>numero recherché: %s</td>"
			    			+ "<td>%s</td>"
			    			+ "<td align='right'>numero islp: %s / %s</td>"
			    			+ "</tr>",t[0],t[1],t[2],t[3]);
					
					builder.append(temp);
				}
				
				// fin html
				builder.append("</TABLE></BODY></HTML>");
				
				resultat = builder.toString();
			}
			else
			{
				resultat = "Aucun résultat";
					
			}
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return resultat;
	}
	
	public dialogRecherche(Frame frame,String titre,boolean modal)
	{
		super(frame,titre,modal);
		super.setSize(920, 600);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		groupRadio = new ButtonGroup();
		
		// parametric
		
		panelParametric = new JPanel();
		panelParametric.setBorder(new TitledBorder(null, "Parametric", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelParametric,BorderLayout.NORTH);
		panelParametric.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		radioFullNumero = new JRadioButton("Recherche sur les numéros en entier");
		radioFullNumero.setSelected(true);
		panelParametric.add(radioFullNumero);
		
		radioLeftNumero = new JRadioButton("Recherche à partir du X eme chiffre");
		radioLeftNumero.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(radioLeftNumero.isSelected())
					comboNombre.setEnabled(true);
				else
					comboNombre.setEnabled(false);
			}
		});
		panelParametric.add(radioLeftNumero);
		
		groupRadio.add(radioFullNumero);
		groupRadio.add(radioLeftNumero);
		
		comboNombre = new JComboBox();
		comboNombre.setEnabled(false);
		comboNombre.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6", "7", "8"}));
		panelParametric.add(comboNombre);
		

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Panel de Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel,BorderLayout.CENTER);
		panel.setLayout(null);
		
		textContact = new JTextArea();
		
		// button
		
		buttonColler = new JButton("Coller");
		buttonColler.addActionListener(this);
		
	
		
		popColler = new popupMenuColler(textContact);
		popColler.addMouseListener(this);
		
		
		textContact.setComponentPopupMenu(popColler);
		
		JScrollPane scrollPane = new JScrollPane(textContact);
		scrollPane.setBounds(39, 55, 182, 405);
		panel.add(scrollPane);
		
		buttonSearch = new JButton("Lancer la recherche\n");
		buttonSearch.setBackground(Color.PINK);
		buttonSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// recherche ancer
				String text = textContact.getText();																																					
				textResult.setText(search(text));
				
			}
		});
		buttonSearch.setBounds(244, 474, 182, 25);
		panel.add(buttonSearch);
		
		 textResult = new JEditorPane();
		 textResult.setContentType("text/html");
		
		//textResult = new JTextArea();
		textResult.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scrollPaneResult = new JScrollPane(textResult);
		scrollPaneResult.setBounds(244, 55, 643, 405);
		panel.add(scrollPaneResult);
		
		buttonPrint = new JButton("Impression");
		buttonPrint.setBackground(Color.CYAN);
		buttonPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				CResultPrint resultPrint = new CResultPrint(backupResultat);
				
				PrinterJob printer = PrinterJob.getPrinterJob();
				
				printer.setPrintable(resultPrint);
				Pageable page;
				
				if(printer.printDialog())
				{
					try {
						printer.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
		buttonPrint.setBounds(770, 474, 117, 25);
		panel.add(buttonPrint);
		
		labelExemple = new JLabel("ex: 32494123456");
		labelExemple.setBounds(39, 28, 182, 15);
		panel.add(labelExemple);
		
		buttonNettoyer = new JButton("Nettoyer");
		buttonNettoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				// nettoyage des numeros introduits
				
				String temp = textContact.getText();
				temp = temp.replace(".", "");
				temp = temp.replace("/", "");
				temp = temp.replace("+", "");
				textContact.setText(temp);
			}
		});
		buttonNettoyer.setBackground(Color.ORANGE);
		buttonNettoyer.setBounds(39, 474, 182, 25);
		panel.add(buttonNettoyer);
		
		

	
		

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		if(arg0.getSource() == buttonColler)
		{
			popup.hide();
			
			//
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable tran = clip.getContents(null);
			
			if (tran != null && tran.isDataFlavorSupported(DataFlavor.stringFlavor)) {

			      String clipboardContent = null;
				try {
					clipboardContent = (String) tran.getTransferData(DataFlavor.stringFlavor);
					
					textContact.setText(clipboardContent);
					
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			   
			    }
			return;
		}
		
		this.dispose();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(arg0.getComponent(), "super test");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
