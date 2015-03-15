package CODE.src.retroAnalyseCode;

public class CData
{
	private String contact;
	private String type;
	private String date;
	private String numero;
	private String nom;
	private String prenom;
	private String datenaissance = new String("00000000"); // force la date de naissance et permet
	// ainsi d'éviter d'avoir des bugs lorsqu'aucune date n'est reprise dans l'islp
	
	public CData(String[] d)
	{
		//if(d.length != 4)
			//return;
		
		 this.setContact(d[0]);
		
		 String temp  = d[1].trim();
		 
			switch(temp)
			{
				case "Information" : this.setType("INFO");
									 break;
									 
				case "Judiciaire non roulage" : this.setType("JUDI");
												break;
												
				case "Apostille" : this.setType("APOS");
				break;
				
				case "Rapport information" : this.setType("RIRS");
				break;
				
				case "Courrier entrant" : this.setType("COUEN");
				break;
				
				case "Administratif" : this.setType("ADMI");
				break;
				
				case "Circulation" : this.setType("JUDI");
				break;
				
				case "Avertissement (PVA)" : this.setType("JUDI");
				break;
				
				case "Accident de roulage" : this.setType("JUDI");
				break;
				
				
				case "Enquête" : this.setType("ENQS");
				break;
												
				case "INFO" : this.setType("INFO");
							   break;
							   
				case "JUDI" : this.setType("JUDI");
							   break;
												
				default:  this.setType("????");
									break;
			}
		
		this.setDate(d[2]);
		this.setNumero(d[3]);
		// nom
		if(d[4] != null && d[4].length() > 0)
			this.setNom(d[4]);
		else
			this.setNom("Neant");
		
		// prenom
		if(d[5] != null && d[5].length() > 0)
			this.setPrenom(d[5]);
		else
			this.setPrenom("Neant");
		// datenaissance
		if(d[6] != null && d[6].length() > 0)
			this.setDatenaissance(d[6]);
		else
			this.setDatenaissance("00000000");
		
	}
	
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getDatenaissance() {
		return datenaissance;
	}



	public void setDatenaissance(String datenaissance) {
		this.datenaissance = datenaissance;
	}



	public String getContact() {
		return contact;
	}
	public void setContact(String contact) 
	{
		this.contact = contact.replace("/", "");
		String temp = this.contact.replace(".", "");
		String temp2 = temp.trim();
		this.contact = temp2;
	
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
