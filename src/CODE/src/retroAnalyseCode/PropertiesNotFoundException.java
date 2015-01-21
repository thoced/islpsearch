package CODE.src.retroAnalyseCode;

public class PropertiesNotFoundException extends Exception
{

	public PropertiesNotFoundException()
	{
		System.out.print("le fichier de configuration n'existe pas");
	}
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

}
