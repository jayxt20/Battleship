import java.util.ArrayList;

public class Bateau {
	
	private ArrayList<String> lesBateaux;
	private ArrayList<String> positions;
	//Constructeur
	public Bateau(){
		this.lesBateaux = new ArrayList<String>();
		this.positions = new ArrayList<String>();
		//Enregistrement des differents bateaux
		this.lesBateaux.add("Porte-avions");
		this.lesBateaux.add("Sous-marin");
		this.lesBateaux.add("Cuirass\u00e8");
		this.lesBateaux.add("Cuirass\u00e8");
		this.lesBateaux.add("Zodiac");
	}
	//Acesseurs
	public ArrayList<String> getLesBateaux(){
		return this.lesBateaux;
	}
	
	public ArrayList<String> getLesPositions(){
		return this.positions;
	}
	//Retourne la taille du bateau en parametre
	public int getTaille(String nom){
		int taille = 0;
		switch (nom){
			case "Porte-avions":{
				taille = 5;
				break;
			}
			case "Sous-marin":{
				taille = 4;
				break;
			}
			case "Cuirass\u00e8":{
				taille = 3;
				break;
			}
			case "Zodiac":{
				taille = 2;
				break;
			}
		}
		return taille;
	}
	
	
}
