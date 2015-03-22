package CODE.src.retroAnalyseCode;

import java.io.*;
import java.sql.SQLException;

import GUI.dialogProgress;

public class CImportFile 
{
	public void Import(File file,dialogProgress diap)
	{
		if(file == null)return;
		
		// instance du CTransaction
		CTransaction transaction = new CTransaction();
		
		// initialisation du progressbar
		diap.getProgressBar().setValue(0);
		diap.getProgressBar().setStringPainted(true);
		
		
		try 
		{
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] bytes = new byte[bis.available()];
			bis.read(bytes);
			
			String fichier = new String(bytes);
			
			String[] lines = fichier.split("\n");
			
			// max du progress bar
			diap.getProgressBar().setMaximum(lines.length);
			int cptbar = 0;
			for(String line : lines)
			{
				// mise à jour de la bare de progression
				diap.getProgressBar().setValue(cptbar);
				// on lit tous les élements séparés par une tabulation
				String[] datas = line.split("\t");
				CData data = new CData(datas);
				cptbar++;
				// insert dans la DB
				try 
				{
					transaction.insertToBase(data);
					
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
