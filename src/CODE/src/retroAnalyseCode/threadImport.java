package CODE.src.retroAnalyseCode;

import java.io.File;

import javax.swing.JOptionPane;

import GUI.dialogProgress;

public class threadImport extends Thread 
{
	private File[] files;
	private dialogProgress diaProgress;
	
	public threadImport(File[] file)
	{
		files = file;
		
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		super.run();
		
		
		// import
		CImportFile cif = new CImportFile();
		if(files !=null)
			{
				for(File f : files)
				{
					cif.Import(f,diaProgress);
				// mise a jour du progress bar
					diaProgress.getProgressBarTotal().setValue(diaProgress.getProgressBarTotal().getValue() + 1);
				}
			}
		
		
		JOptionPane.showMessageDialog(null, String.format("Importation des %d fichier(s) réussie",files.length));
		// fermeture de la boite de dialog progressbar
		diaProgress.setVisible(false);
		
	}
	
	public void setDialogProgress(dialogProgress diap)
	{
		diaProgress = diap;
	}

	

}
