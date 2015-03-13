package CODE.src.retroAnalyseCode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CTransaction 
{
	private Connection con = null;
	private String sqlInsert = "insert into db_islp.t_islp_info (contact,type,annee,numero,nom,prenom,datenaissance) values (?,?,?,?,?,?,?)";
	private String sqlDelete = "delete from db_islp.t_islp_search";
	private String sqlInsertToSearch = "insert into db_islp.temp_islp_search (contact) values (?)";
	
	private String sqlSearch = "select contact,type,annee,numero,nom,prenom,datenaissance from db_islp.t_islp_info where contact IN ( select contact from db_islp.temp_islp_search) order by contact";
	private String sqlSearchLeft = "select contact,type,annee,numero,nom,prenom,datenaissance from db_islp.t_islp_info where SUBSTR(contact,%d) IN ( select SUBSTR(contact,%d) from db_islp.temp_islp_search) order by contact";
	
	private String sqlGetRecords = "select contact,type,annee,numero,nom,prenom,datenaissance from db_islp.t_islp_info ORDER by annee";
	

	private String  temporydrop = "DROP TEMPORARY TABLE IF EXISTS `db_islp`.`temp_islp_search`";
	private String  tempory = "CREATE TEMPORARY TABLE IF NOT EXISTS `db_islp`.`temp_islp_search` ("
			+ "`id` BIGINT NOT NULL AUTO_INCREMENT ,"
			+ "`contact` VARCHAR(32) NULL ,"
			+ "PRIMARY KEY (`id`) )"
			+ "ENGINE = MyISAM";
	
	
	private String sqlNbTuple = "select count(*) from db_islp.t_islp_info";
	public CTransaction()
	{
		con = DossierConnection.getConn();
	}
	
	public String getAllRecords() throws SQLException
	{
		
		StringBuilder sbuilder = null;
		
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(sqlGetRecords);
			
			// instance du records
			result.last();
			sbuilder = new StringBuilder();
			result.beforeFirst();
			
			int i=0;
			while(result.next())
			{
				String contact = result.getString(1);
				String type = result.getString(2);
				String annee = result.getString(3);
				String numero = result.getString(4);
				String nom = result.getString(5);
				String prenom = result.getString(6);
				String datenaissance = result.getString(7);
				
				sbuilder.append(String.format("%s	%s	%s	%s	%s	%s	%s\n",contact,type,annee,numero,nom,prenom,datenaissance));
				i++;
			}
			
		}
		
		return sbuilder.toString();
	}
	
	public void insertToBase(CData data) throws SQLException
	{
		if(con != null)
		{
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, data.getContact());
			ps.setString(2, data.getType());
			ps.setString(3, data.getDate());
			ps.setString(4, data.getNumero());
			ps.setString(5, data.getNom());
			ps.setString(6, data.getPrenom());
			ps.setString(7, data.getDatenaissance());
			
			// exécution de la requete
			ps.execute();
			ps.close();
		}
	}
	
	public long getNombreTupleIslp() throws SQLException
	{
		long nb = 0;
		
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlNbTuple);
			
			rs.first();
			String nbTuple= rs.getString(1);
			
			nb= Long.parseLong(nbTuple);
		}
		
		return nb;
	}
	
	// methode de recherche
	
	public String[] search(String[] listes,int typeRecherche,int nbChiffre) throws SQLException
	{
		String[] ret = null;
		
		
		
		
		// *******************************************
		
		if(con != null)
		{
			
			// creation de la table temporaire /////////// pour une utilisation multiusers
			
			
			
			Statement sc = con.createStatement();
			
			// suppression de la table temporary
			sc.execute(temporydrop);
			// creation de la nouvelle table temporary
			sc.execute(tempory);
			
			String p = "CREATE INDEX `index_contact` ON `db_islp`.`temp_islp_search` (`contact` ASC);"; 
			sc.execute(p);
			
			// delete de la table t_islp_search
			/*Statement st = con.createStatement();
			
			st.executeUpdate(sqlDelete);
			st.close();
	*/
		// ajout des contacts a rechercher dans la table temp_islp_search
			
			PreparedStatement ps = con.prepareStatement(sqlInsertToSearch);
			
			for(String l : listes)
			{
				// nettoyage
				String temp = l.trim();
				ps.setString(1,temp);
				ps.execute();
			}
		
			ps.close();
			
		// Recherche en croisant les deux tables
			
			ResultSet result = null;
			Statement stSearch = null;
			
			switch(typeRecherche)
			{
			case 1 : stSearch = con.createStatement();
					 result = stSearch.executeQuery(sqlSearch); 
					 break;
					 
			case 2 : stSearch = con.createStatement();
					 sqlSearchLeft = String.format(sqlSearchLeft, nbChiffre,nbChiffre);
					 result = stSearch.executeQuery(sqlSearchLeft);
					 break;
			}
			
			
			
			
		// on determine le nombre de résultats
			result.last();
			int nb = result.getRow();
		// creation de l'instance des resultats de retour
		    ret = new String[nb];
		    
		    int cpt = 0;
		    result.beforeFirst();
		    while(result.next())
		    {
		    	String contact 	= result.getString(1);
		    	String type    	= result.getString(2);
		    	String annee 	= result.getString(3);
		    	String numero	= result.getString(4);
		    	String nom 		= result.getString(5);
				String prenom 	= result.getString(6);
				String datenaissance = result.getString(7);
		    	// sous forme html
		    	//ret[cpt] = String.format("numero recherché : %s - type : %s - numero islp : %s-%s \n",contact,type,numero,annee);
		    /*	ret[cpt] = String.format("<tr>"
		    			+ "<td>numero recherché: %s</td>"
		    			+ "<td>%s</td>"
		    			+ "<td align='right'>numero islp: %s / %s</td>"
		    			+ "</tr>",contact,type,numero,annee);*/
		    	
		    	ret[cpt] = String.format("%s	%s	%s	%s	%s	%s	%s",contact,type,numero,annee,nom,prenom,datenaissance);
		    	
		    	cpt++;
		    }
				
			
			
			stSearch.close();
			
			
		}
		return ret;
	}
}