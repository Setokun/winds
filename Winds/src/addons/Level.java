package addons;

public class Level {

	public String adresseImage;
	public String titreNiveau;
	
	public Level(String adresseImage, String titreNiveau){
		this.adresseImage = adresseImage;
		this.titreNiveau = titreNiveau;
	}
	
	public String toString(){
		return titreNiveau+" "+adresseImage;
	}
}
