package CODE.src.retroAnalyseCode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Hashtable;

import javax.swing.JOptionPane;


public class DossierConnection
{
	private static  Connection conn;
	
	private static String USER = "root";
	private static String PASS = "bombe123";
	
	public static void testScript()
	{
		
	}
	public static void Dispose()
	{
		try {
			if(conn != null && !conn.isClosed())
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Finalize()
	{
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static Connection getConn() 
	{
		
		
		try
		{
			// chargement du properties
			CConfig config = new CConfig();
			
			
			if(conn == null || conn.isClosed())
			{
				Class.forName(config.getDriver());
				String url = config.getUrl();
				conn = DriverManager.getConnection(url,config.getLogin(),config.getPassword());
				
				
			}
		}
		
		catch(ClassNotFoundException | SQLException cnfe)
		{
			cnfe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertiesNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void setConn(Connection c) {
		conn = c;
	}
	
	public static void ExecuteScript()
	{
		try
		{
			InputStream is = DossierConnection.class.getResourceAsStream("/SCRIPTS/script.sql");
			byte[] b = new byte[is.available()];
			is.read(b);
			String requete = new String(b,"UTF-8");
			
			// exécution des différentes requetes présente dans le script
			String[] ds = requete.split(";");
			Statement st = DossierConnection.getConn().createStatement();
			for(String req : ds)
			{
				try
				{
					st.execute(req);
				}
				catch(SQLException sqe)
				{
					sqe.printStackTrace();
				}
			}
			
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}