package CODE.src.retroAnalyseCode;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class CResultPrint implements Printable 
{
	private String texte;
	private String[] listeTexte = null;
	
	int NB_MAX_PER_PAGE = 25;
	int START_PIXEL = 16;
	int ECARD = 24;
	
	int maxPage = 0;
	
	public CResultPrint(String[] t)
	{
		
		if(t!=null)
		{
			listeTexte = t;
		
			// on determine le nombre de page
			maxPage = ((int)listeTexte.length / NB_MAX_PER_PAGE);
		}
		
	}

	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int nummerPage) throws PrinterException
	{
	
		if(nummerPage > maxPage)return Printable.NO_SUCH_PAGE;
		//arg0.drawString("super super super super super ",(int) arg1.getImageableX(), (int)arg1.getImageableY());

		 Graphics2D g2d = (Graphics2D)arg0;
		 
		 g2d.translate(arg1.getImageableX(), arg1.getImageableY());
		 
		 int cpt = START_PIXEL;
		 for(int i=nummerPage * NB_MAX_PER_PAGE;(i<(nummerPage * NB_MAX_PER_PAGE) + NB_MAX_PER_PAGE) && i < listeTexte.length;i++)
		 {
			 String l = listeTexte[i];
			 String[] f = l.split("\t");
			 
			 			 
			// création de la date de naissance correcte
				String annee = f[6].substring(0, 4);
				String mois = f[6].substring(4,6);
				String jour = f[6].substring(6,8);
				String dd = String.format("%s/%s/%s", jour,mois,annee);
			 
			 String phrase = String.format("%s - Type : %s , numero : %s/%s  %s %s %s", f[0],f[1],f[2],f[3],f[4],f[5],dd);
			 
			 g2d.drawString(phrase, 10, cpt);
			 cpt += ECARD;
		 }
		return Printable.PAGE_EXISTS;
	}

}
