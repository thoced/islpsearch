package GUI;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;

public class dialogProgress extends JDialog 
{
	private JProgressBar progressBarTotal;
	private JProgressBar progressBar;
	public dialogProgress(Frame frame,String titre,boolean modal)
	{
		super(frame,titre,modal);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		
		this.setSize(600, 196);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		progressBarTotal = new JProgressBar();
		progressBarTotal.setBounds(144, 40, 300, 32);
		panel.add(progressBarTotal);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(144, 136, 300, 32);
		panel.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Progression des fichiers:");
		lblNewLabel.setBounds(204, 12, 197, 15);
		panel.add(lblNewLabel);
		
		JLabel lblProgressionDuFichier = new JLabel("Progression du fichier en cours:");
		lblProgressionDuFichier.setBounds(188, 95, 232, 15);
		panel.add(lblProgressionDuFichier);
		
		this.setLocationRelativeTo(frame);
		

	}
	
	
	public JProgressBar getProgressBarTotal()
	{
		return progressBarTotal;
	}
	public void setProgressBarTotal(JProgressBar progressBarTotal)
	{
		this.progressBarTotal = progressBarTotal;
	}


	public JProgressBar getProgressBar() {
		return progressBar;
	}


	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}
