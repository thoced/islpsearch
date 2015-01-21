package CODE.src.retroAnalyseCode;

public class CData
{
	private String contact;
	private String type;
	private String date;
	private String numero;
	
	public CData(String[] d)
	{
		if(d.length != 4)
			return;
		
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
				
				case "Rapport d'information" : this.setType("RIRS");
				break;
				
				case "Courrier entrant" : this.setType("COUEN");
				break;
				
				case "Enquete" : this.setType("ENQS");
				break;
												
				case "INFO" : this.setType("INFO");
							   break;
							   
				case "JUDI" : this.setType("JUDI");
							   break;
												
				default:  this.setType("????");
									break;
			}
		
		date = d[2];
		numero= d[3];
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
