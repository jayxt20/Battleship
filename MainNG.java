import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainNG {

	//Declaration des variables et instanciation des variables
	public static Scanner clavier = new Scanner(System.in);
	public static String grille1[][] = new String[10][10];
	public static String grille2[][] = new String[10][10];
	public static Bateau bat1 = new Bateau();
	public static Bateau bat2 = new Bateau();
	public static ArrayList<String> lesBateaux1 = bat1.getLesBateaux();
	public static ArrayList<String> lesBateaux2 = bat2.getLesBateaux();
	public static ArrayList<String> batPla1 = new ArrayList<String>();
	public static ArrayList<String> batPla2 = new ArrayList<String>();
	public static ArrayList<String> positionsBat1 = bat1.getLesPositions();
	public static ArrayList<String> positionsBat2 = bat2.getLesPositions();
	public static int mode=-1;
	public static int type=-1;
	public static boolean game = true;
	public static String j1;
	public static String j2;
	public static final JFrame JFrame = new JFrame();
	//Main
	public static void main(String[] args) {
		do{
		initGrille();
			menuMode();
			menuType();
			switch(mode){
			//Si le mode est Joueur versus Ordi
				case 2:{
					j1 = "J1";
					j2 = "IA";
					//Joueur place ces bateaux
					menuBateau(lesBateaux1, grille1, positionsBat1);
					//L'Ordi place un bateaux
					IA ia2 = new IA(grille2,1);
					grille2 = ia2.getGrille();
					lesBateaux2 = ia2.getLesBateaux();
					positionsBat2 = ia2.getPositionsBat();
					//Tant que tout les bateaux d'un grille ne sont pas supprimer
					while(game == true){
						//Le Joueur commence
						//Le Joueur frappe
						System.out.println("J1 saisir frappe");
						saisirFrappe(positionsBat2,grille2,batPla2);
						affichGrilleAD(grille2);
						//Si le jeux n'est pas fini
						if(game == true){
							//L'Ordi frappe
							System.out.println("IA saisir frappe");
							grille1 = ia2.frappe(grille1);
							affichGrilleAD(grille1);
						}	
					}
				break;
				}
			//Si le mode est Joueur versus Joueur
				case 3:{
					j1 = "J1";
					j2 = "J2";
					//Joueur 1 place ces bateaux
					menuBateau(lesBateaux1, grille1, positionsBat1);
					//Joueur 2 place ces bateaux
					menuBateau(lesBateaux2, grille2, positionsBat2);
					//Tant que tout les bateaux d'un grille ne sont pas supprimer
					while(game == true){
						//Le Joueur 1 commence
						//Le Joueur 1 frappe
						System.out.println("J1 saisir frappe");
						saisirFrappe(positionsBat2,grille2,batPla2);
						affichGrilleAD(grille2);
						//Si le jeux n'est pas fini
						if(game == true){
							//Le Joueur 2 frappe
							System.out.println("J2 saisir frappe");
							saisirFrappe(positionsBat1,grille1,batPla1);
							affichGrilleAD(grille1);
						}
					}
				break;
				}
			//Si le mode est Demo
				case 1:{
					j1 = "IA1";
					j2 = "IA2";
					//L'Ordi 1 place ces bateaux
					IA ia1 = new IA(grille1,1);
					grille1 = ia1.getGrille();
					lesBateaux1 = ia1.getLesBateaux();
					positionsBat1 = ia1.getPositionsBat();
					//L'Ordi 1 place ces bateaux
					IA ia2 = new IA(grille2,1);
					grille2 = ia2.getGrille();
					lesBateaux2 = ia2.getLesBateaux();
					positionsBat2 = ia2.getPositionsBat();
					//Tant que tout les bateaux d'un grille ne sont pas supprimer
					while(game == true){
						//L'Ordi 1 commence
						//L'Ordi 1 frappe
						System.out.println("IA1 saisir frappe");
						grille2 = ia1.frappe(grille2);
						affichGrille(grille2);
						//Si le jeux n'est pas fini
						if(game == true){
							//L'Ordi 1 frappe
							System.out.println("IA2 saisir frappe");
							grille1 = ia2.frappe(grille1);
							affichGrille(grille1);
						}
					}
				break;
				}
			}
				
		}while(mode==0);
	}	
	//Initialier grile
	public static void initGrille(){
		int i,j;
		//On remplis la grille de 0
		for(i=1;i<10;i++){
			j= (i-1)+65;
			grille1[0][i]=(Character.toString((char)j));
			grille2[0][i]=(Character.toString((char)j));
		}
		for(i=0;i<10;i++){
			grille1[i][0]=Integer.toString(i);
			grille2[i][0]=Integer.toString(i);
		}
		for(i=1;i<10;i++){
			for(j=1;j<10;j++){
				grille1[i][j]="0";
				grille2[i][j]="0";
			}
		}
		grille1[0][0]= "-";
		grille2[0][0]= "-";
		
	}
	//Afficher la grille du joueur 
	public static void affichGrille(String grille[][]){
		//Affiche le nom du propietair de la grille
		if(grille.equals(grille1)){	
			System.out.println("Grille " + j1);
		}else{
			System.out.println("Grille " + j2);
		}
		//Parcours la tableau pour l'afficher
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(grille[i][j]+" ");
			}
			System.out.println("");
		}
	}
	//Affiche la grille adverse
	public static void affichGrilleAD(String grille[][]){
		//Affiche le nom du propietair de la grille
		if(grille.equals(grille1)){	
			System.out.println("Grille " + j1);
		}else{
			System.out.println("Grille " + j2);
		}
		//Parcours la tableau pour l'afficher
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(grille[i][j].equals("1")){
					System.out.print("0 ");
				}else{
					System.out.print(grille[i][j] + " ");					
				}
			}
			System.out.println("");
		}
	}
	//Affiche le menu du mode du jeu
	public static void menuMode(){
		System.out.println("-----MODE-----");
		System.out.println("1- D\u00e9mo");
		System.out.println("2- J VS IA");
		System.out.println("3- J VS J");
		mode = saisirnb(1,3,"nombre");
	}
	//Affiche le menu du type du jeu
	public static void menuType(){
		System.out.println("-----Type-----");
		System.out.println("1- Bataille Navale (classique)");
		System.out.println("2- Mission Radar");
		System.out.println("3- Op\u00e9ration Artillerie");
		System.out.println("4- Alerte rouge");
		type = saisirnb(1,4,"nombre");
	}
	//Affiche la liste des bateaux a placer
	public static void menuBateau(ArrayList<String> lesBat, String grille[][], ArrayList<String> pBat){
		//Declaration et initialisation du choix
		int choix = -1;
		//Affiche le non du joueur qui dois placer les bateaux
		if(lesBat.equals(lesBateaux1)){
			System.out.println("J1");
		}else{
			System.out.println("J2");
		}
		//Affiche la liste des bateaux a placer
		System.out.println("-----Les bateaux-----");
		//Si le choix est de 0 le placement des bateaux est fini
		while(choix != 0){
			//Liste des bateaux
			for(int i=0;i<lesBat.size();i++){
				System.out.println((i+1) + "- " + lesBat.get(i));
			}
			//Il peut arreter la saisie des bateaux seulement si il a deja placer un bateau
			if(lesBat.size() != 5){
				System.out.println("0- Fin du positionnement");
				choix=saisirnb(0,lesBat.size(),"nombre");
			}else{
				choix=saisirnb(1,lesBat.size(),"nombre");
			}
			//Si il choisi un bateau on le fait placer
			if(choix!=0){	
				placerBateaux(grille, pBat, lesBat.get(choix-1), lesBat);
			}
		}
		
	}
	//Fonction de saisie d'un nombre
	public static int saisirnb(int borneinf, int bornesup, String txt){
		//Declaration du chiffre a retourner
		int nb;
		//Saisie du chiffre
		System.out.println("Saisir le " + txt);
		nb = clavier.nextInt();
		//Dans la boucle tant que le chiffre n'est pas bon
		while(nb<borneinf || nb>bornesup){
			JOptionPane.showMessageDialog(JFrame,"Saisir un nombre entre " + borneinf + " et " + bornesup,"ERREUR DE SAISIE",JOptionPane.PLAIN_MESSAGE);
			nb = clavier.nextInt();
		}
		//Retourne le chiffre saisie
		return(nb);
	}
	//Fonction de saisie d'une lettre
	public static int saisirLettre(){
		//Declaration des variables
		char c;
		String s = "";
		int ascii;
		//Mise a zero pour eviter les beugs
		clavier.nextLine();
		System.out.println("Saisir la coordon1e vertical (A \u00e0 I)");
		//Saisi de la lettre
		//Mise en majuscule pour eviter les beugs avec le code ascci
		s = clavier.nextLine().toUpperCase();
		//Transformation en int pour une gestion plus facile de la grille
		c = s.charAt(0);
		ascii = (int) c;
		//Si la lettre saisie n'est pas entre A et I 
		//Refaire la saisie
		while(ascii<65 || ascii>73){
			JOptionPane.showMessageDialog(JFrame,"Saisir une lettre entre A et I","ERREUR DE SAISIE",JOptionPane.PLAIN_MESSAGE);
			s = clavier.nextLine().toUpperCase();
			c = s.charAt(0);		
			ascii = (int) c;
		}
		//Retourne le chiffre corespondant a l'emplacement dans la grille
		return ascii-64;
	}
	//Saisie de l'emplacement de la frappe
	public static void saisirFrappe(ArrayList<String> pBat, String grille[][], ArrayList<String> batPla){
		//Declaration des variables
		int y,x;
		//Saisie de l'emplacement de la frappe
		do{
			x = saisirLettre();
			y = saisirnb(1,9,"nombre correspondant \u00e0 la coordonn\u00e9e horizontal");
			//Si il a deja frapper dans la cordone il resaisie
			if(!grille[y][x].equals("0") && !grille[y][x].equals("1")){
				System.out.println("Position d\u00e9l\u00e0 bombarder");
			}
		//Tant qu'il n'a pas saisie une cordone correcte
		}while(!grille[y][x].equals("0") && !grille[y][x].equals("1"));
		//Si il n'y a rien a l'emplacement on indique dans la grille qu'il a deja frapper ici
		switch(grille[y][x]){
			case "0":{
				if(grille.equals(grille1)){
					grille1[y][x] = "2";
				}else{
					grille2[y][x] = "2";
				}
				grille[y][x] = "2";
				break;
			}
			//Si il y'a un bateau a l'emplacement on indique qu'il a toucher et on l'indique dans la grille
			case "1":{
				if(grille.equals(grille1)){
					grille1[y][x] = "3";
				}else{
					grille2[y][x] = "3";
				}
				grille[y][x] = "3";
				//Message pour lui dire qu'il a toucher
				System.out.println("FRAPPE REUSSITE !!!!!");
				//On verifie s'il n'a pas couler un bateau
				toucheCoule(pBat, grille, batPla);
				break;
			}
		}
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
	//On verifi si il a couler un bateau ou pas
	public static void toucheCoule(ArrayList<String> pBat, String grille[][], ArrayList<String> batPla){
		//Declaration des variables
		int y,x,yy,xx,i;
		boolean tr, coule;
		char[] posBat;
		//Initialiser les varables
		i = 0;
		tr = false;
		coule = false;
		//On parcours toutes les positions des bateaux
		while(i<pBat.size() && coule == false){
			//On recupere les parametres du bateau
			posBat = pBat.get(i).toCharArray();
			//Initialisation des coordones
			y = (int) posBat[0]-48;
			x = (int) posBat[1]-48;
			yy = (int) posBat[2]-48;
			xx = (int) posBat[3]-48;
			//On parcous la position du bateau
			//Et on verifie si le bateau a était toucher entierement ou pas
			if(yy==y){
				if(xx<x){
					while((!grille[y][xx].equals("0") && !grille[y][xx].equals("1")) && tr == false && xx<x){
						xx++;
						if(grille[y][xx].equals("0") && grille[y][xx].equals("1")){
							tr = true;
						}
					}
				}else{
					if(xx>x){
						while((!grille[y][xx].equals("0") && !grille[y][xx].equals("1")) && tr == false && xx>x){
							xx--;
							if(grille[y][xx].equals("0") && grille[y][xx].equals("1")){
								tr = true;
							}
						}
					}
				}
			}else{
				if(xx==x){
					if(yy<y){
						while((!grille[yy][x].equals("0") && !grille[yy][x].equals("1")) && tr == false && yy<y){
							yy++;
							if(grille[yy][x].equals("0") && grille[yy][x].equals("1")){
								tr = true;
							}
						}
					}else{
						if(yy>y){
							while((!grille[yy][x].equals("0") && !grille[yy][x].equals("1")) && tr == false && yy>y){
								yy--;
								if(grille[yy][x].equals("0") && grille[yy][x].equals("1")){
									tr = true;
								}
							}
						}
					}
				}
			}
			//Si un bateau a etait coule 
			if((yy==y && xx==x) || tr == true){
				//On suprime la position du bateau de la liste
				if(grille.equals(grille1)){
					//On affiche au joueur
					JOptionPane.showMessageDialog(JFrame,"Bravo " + j2 + " \u00e0 coul\u00e9 un bateau","Bingo",JOptionPane.PLAIN_MESSAGE);
					pBat.remove(i);
					positionsBat2 = pBat;
				}else{
					if(grille.equals(grille2)){
						//On affiche au joueur
						JOptionPane.showMessageDialog(JFrame,"Bravo " + j1 + " \u00e0 coul\u00e9 un bateau","Bingo",JOptionPane.PLAIN_MESSAGE);
						pBat.remove(i);
						positionsBat1 = pBat;
					}
				}
				//On change la valeur de coule pour sortir de la boucle
				coule = true;
			}
			//On incremente pour passer au bateu suivant
			i++;
		}
		//On verifie si tout les bateaux sont tous couler 
		if(positionsBat2.isEmpty()){
			//Si tous les bateaux de la grille2 sont coules le J1 a gagner
			JOptionPane.showMessageDialog(JFrame, j1 + " à gagner","Bravo",JOptionPane.PLAIN_MESSAGE);
			game=false;
		}else{
			//Si tous les bateaux de la grille1 sont coules le J2 a gagner
			if(positionsBat1.isEmpty()){
				JOptionPane.showMessageDialog(JFrame,j2 + " à gagner","Bravo",JOptionPane.PLAIN_MESSAGE);
				game=false;
			}
		}
	}
	//Placer le bateau dans la grille selon les parametres selectionner du joueur
	public static void placeDGrille(ArrayList<String> positions, int y, int x, String nom, String grille[][], ArrayList<String> positionsBat, ArrayList<String> lesBat){
		//Proposition des differents choix possible de placement
		for(int z=0; z<positions.size();z++){
			System.out.println(z+1 + "- " + positions.get(z));
		}
		//Declaration et initialisation des variables
		int choix = saisirnb(1,positions.size(),"choix de la position");
		int yy = positions.get(choix-1).charAt(1)-48;
		int xx = (int) positions.get(choix-1).charAt(0)-64;
		//Remplissage de la grille 
		grille[yy][xx] = "1";
		
		positionsBat.add(String.valueOf(y) + "" + String.valueOf(x) + "" + String.valueOf(yy) + "" + String.valueOf(xx));
		if(grille.equals(grille1)){
			positionsBat1.add(String.valueOf(y) + "" + String.valueOf(x) + "" + String.valueOf(yy) + "" + String.valueOf(xx));
			grille1 = grille;
		}else{
			positionsBat2.add(String.valueOf(y) + "" + String.valueOf(x) + "" + String.valueOf(yy) + "" + String.valueOf(xx));
			grille2 = grille;
		}
		while(yy!=y || xx!=x){
			if(yy==y){
				if(xx<x){
					xx++;
				}else{
					xx--;
				}
				if(grille.equals(grille1)){
					grille1[y][xx] = "1";
				}else{
					grille2[y][xx] = "1";
				}
			}else{
				if(yy<y){
					yy++;
				}else{
					yy--;
				}
				if(grille.equals(grille1)){
					grille1[yy][x] = "1";
				}else{
					grille2[yy][x] = "1";
				}
			}
		}
		if(grille.equals(grille1)){
			grille1[y][x] = "1";
		}else{
			grille2[y][x] = "1";
		}
		//On affiche la grille pour que l'utilisateur voie la position du bateau
		affichGrille(grille);
		//On enregistre le bateau comme etant placer
		
		if(lesBat.equals(lesBateaux1)){
			batPla1.add(nom);
		}else{
			batPla2.add(nom);
		}
		lesBat.remove(nom);
	}
	//Traitement de la premier cordonne saisie par l'utilisateur
	public static void choixPlacement(int y,int x, String nom, String grille[][],  ArrayList<String> positionsBat, ArrayList<String> lesBat){
		//Declaration et initialisation des variables
		ArrayList<String> positions = new ArrayList<String>();
		int tailleB = bat1.getTaille(nom);
		//On enregistre les differentes positions possible
		if((y-tailleB)>=0 && grille[y-tailleB+1][x].equals("0") && positionBateau(y,y-tailleB+1,x,x,grille)==false){
			positions.add(Character.toString((char)(x+64))+ (y-tailleB+1));
		}
		if((x-tailleB)>=0 && grille[y][x-tailleB+1].equals("0") && positionBateau(y,y,x,x-tailleB+1,grille)==false){
			positions.add(Character.toString((char)(x+64-tailleB+1))+y);
		}
		if((x+tailleB)<=10 && grille[y][x+tailleB-1].equals("0") && positionBateau(y,y,x,x+tailleB-1,grille)==false){
			positions.add(Character.toString((char)(x+64+tailleB-1))+y);
		}
		if((y+tailleB)<=10 && grille[y+tailleB-1][x].equals("0") && positionBateau(y,y+tailleB-1,x,x,grille)==false){
			positions.add(Character.toString((char)(x+64))+(y+tailleB-1));
		}
		//Si aucune position n'est proposer
		if(positions.size()!=0){
			//Sinon il est rediriger pour finir de placer le bateau
			placeDGrille(positions,y, x, nom, grille, positionsBat, lesBat);
		}else{
			//Il refait une saisie
			JOptionPane.showMessageDialog(JFrame,"Impossible de placer le " + nom + " ici!!","ERREUR DE SAISIE",JOptionPane.PLAIN_MESSAGE);
			placerBateaux(grille, positionsBat, nom, lesBat);			
		}
	}
	//Saisie de la premier cordonne du bateau
	public static void placerBateaux(String grille[][], ArrayList<String> positionsBat, String nom, ArrayList<String> lesBat){
		//Declaration et initialisation des variables
		int x,y;
		boolean tr = false;
		System.out.println("Placer bateau "+ nom+ " de taille "+ bat1.getTaille(nom));
		//Il saisie tant que l'emplacement n'est pas correcte
		while (tr == false){
			//Saisie cordonne
			x = saisirLettre();
			y = saisirnb(1,9,"nombre correspondant \u00e0 la coordonn\u00e9e horizontal");
			//Si il y'a un bateau ici
			if(!grille[y][x].equals("0")){
				//Message d'erreur
				//Refaire la saisie
				JOptionPane.showMessageDialog(JFrame,"Il y'a deja un bateau ici","ERREUR DE SAISIE",JOptionPane.PLAIN_MESSAGE);
			}else{
				//Si il n'y a pas deja un bateau, continue de la saisie
				choixPlacement(y, x, nom, grille, positionsBat, lesBat);
				//Changement de la variable trouver pour sortir du while
				tr = true;
			}
		}
	}
}
