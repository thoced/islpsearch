package CODE.src.retroAnalyseCode;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DebugScript
{

	public void createDebugFile()
	{
		String contact 	= "32494/25.24.85";
		String type		= "Information";
		String annee	= "2014";
		String numero	= "54857";
		
		
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream("test.txt");
			
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			for(int i=0;i<50;i++)
			{
				String temp = String.format("%s	%s	%s	%s\n",contact,type,annee,numero);
				bos.write(temp.getBytes());
			}
			
			contact = "32494/38.64.61";
			numero  = "6666";
			
			for(int i=0;i<50;i++)
			{
				String temp = String.format("%s	%s	%s	%s\n",contact,type,annee,numero);
				bos.write(temp.getBytes());
			}
			
			bos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
	}
}
