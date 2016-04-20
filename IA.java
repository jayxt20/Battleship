import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class IA {

	private Bateau bat;
	private ArrayList<String> lesBateaux;
	private ArrayList<String> batPla;
	private ArrayList<String> positionsBat;
	private static String grille[][] = new String[10][10];
	private int nbBat;
	private JFrame JFrame = new JFrame();
	private Random r;
	
	public IA(String grille[][]){
		this.bat = new Bateau();
		this.lesBateaux = bat.getLesBateaux();
		this.batPla = new ArrayList<String>();
		this.positionsBat = bat.getLesPositions();
		this.grille = grille;
		this.r = new Random();
		this.nbBat = 1 + this.r.nextInt(5 - 1);
		placerBat();
	}
	public IA(String grille[][],int nbBateau){
		this.bat = new Bateau();
		this.lesBateaux = bat.getLesBateaux();
		this.batPla = new ArrayList<String>();
		this.positionsBat = bat.getLesPositions();
		this.grille = grille;
		this.r = new Random();
		this.nbBat = nbBateau;
		placerBat();
	}
	public static void initGrille(){
		int i,j;
		for(i=1;i<10;i++){
			j= (i-1)+65;
			grille[0][i]=(Character.toString((char)j));
		}
		for(i=0;i<10;i++){
			grille[i][0]=Integer.toString(i);
		}
		for(i=1;i<10;i++){
			for(j=1;j<10;j++){
				grille[i][j]="0";
			}
		}
		grille[0][0]= "-";
		
	}
	//Afficher la grille du joueur 
	public static void affichGrille(String grille[][]){
		//Affiche le nom du propietair de la grille
		//Parcours la tableau pour l'afficher
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(grille[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	public String[][] frappe(String grille2[][]){
		int y,x;
		ArrayList<String> positionsPro = new ArrayList<String>();
		do{	
			y =  1 + this.r.nextInt(10 - 1);
			x =  1 + this.r.nextInt(10 - 1);
		}while(!grille2[y][x].equals("0") && !grille2[y][x].equals("1"));
		
		switch(grille2[y][x]){
			//Si il y'a rien on indique sur la grille qu'il a deja frapper ici
			case "0":{
				grille2[y][x] = "2";
				break;
			}
			//Si il y'a un bateau a l'emplacement on indique qu'il a toucher et on l'indique dans la grille
			case "1":{
				grille2[y][x] = "3";
				//Message pour lui dire qu'il a toucher
				JOptionPane.showMessageDialog(JFrame,"TOUCHE","FRAPPE REUSSITE",JOptionPane.PLAIN_MESSAGE);
				//On verifie s'il n'a pas couler un bateau
				MainNG.toucheCoule(positionsBat, grille, batPla);
				break;
			}
		}
		return grille2;
	}
	
	private void placerBat(){
		int y,x,i,indxBat;
		boolean position = false;
		ArrayList<String> positionsPro = new ArrayList<String>();
		for(i=0;i<nbBat;i++){
				
			do{	
				y =  1 + this.r.nextInt(10 - 1);
				x =  1 + this.r.nextInt(10 - 1);
				indxBat = 0 + this.r.nextInt(lesBateaux.size()-0);
				int tailleB = bat.getTaille(lesBateaux.get(indxBat));
				//On enregistre les differentes positions possible
				if((y-tailleB)>=0 && grille[y-tailleB+1][x].equals("0") && positionBateau(y,y-tailleB+1,x,x,grille)==false){
					positionsPro.add(Character.toString((char)(x+64))+ (y-tailleB+1));
				}
				if((x-tailleB)>=0 && grille[y][x-tailleB+1].equals("0") && positionBateau(y,y,x,x-tailleB+1,grille)==false){
					positionsPro.add(Character.toString((char)(x+64-tailleB+1))+y);
				}
				if((x+tailleB)<=10 && grille[y][x+tailleB-1].equals("0") && positionBateau(y,y,x,x+tailleB-1,grille)==false){
					positionsPro.add(Character.toString((char)(x+64+tailleB-1))+y);
				}
				if((y+tailleB)<=10 && grille[y+tailleB-1][x].equals("0") && positionBateau(y,y+tailleB-1,x,x,grille)==false){
					positionsPro.add(Character.toString((char)(x+64))+(y+tailleB-1));
				}
				if(!positionsPro.isEmpty()){
					position = true;
				}
			}while(!grille[y][x].equals("0") && position == false);
				
			int choix =  0 + this.r.nextInt(positionsPro.size()-0);
			//On enregistre le bateau comme etant placer
			batPla.add(lesBateaux.get(indxBat));
			lesBateaux.remove(indxBat);
			position = false;
			int yy = positionsPro.get(choix).charAt(1)-48;
			int xx = (int) positionsPro.get(choix).charAt(0)-64;
			//Remplissage de la grille 
			grille[yy][xx] = "1";
			
			positionsBat.add(String.valueOf(y) + "" + String.valueOf(x) + "" + String.valueOf(yy) + "" + String.valueOf(xx));
			while(yy!=y || xx!=x){
				if(yy==y){
					if(xx<x){
						xx++;
					}else{
						xx--;
					}
					grille[y][xx] = "1";
				}else{
					if(yy<y){
						yy++;
					}else{
						yy--;
					}
					grille[yy][x] = "1";
				}
			}
			grille[y][x] = "1";
		}
	}
	public Bateau getBat() {
		return bat;
	}
	public ArrayList<String> getLesBateaux() {
		return lesBateaux;
	}
	public ArrayList<String> getBatPla() {
		return batPla;
	}
	public ArrayList<String> getPositionsBat() {
		return positionsBat;
	}
	public static String[][] getGrille() {
		return grille;
	}
		//On verifie s'il n'a pas deja un bateau dans les cordonnes saisie
		public static boolean positionBateau(int y, int yy, int x, int xx, String grille[][]){
			//Declaration et initialisation de la variable a retouner
			boolean tr = false;
			//On parcours la grille
			while(yy!=y || xx!=x){
				if(yy==y){
					if(xx<x){
						xx++;
					}else{
						xx--;
					}
				}else{
					if(yy<y){
						yy++;
					}else{
						yy--;
					}
				}
				//Si il y'a deja un bateau ici
				if(grille[yy][xx]!="0"){
					tr = true;
				}
			}
			//Retourn vrai si il y'a un bateau sinon faux
			return tr;
		}
}
