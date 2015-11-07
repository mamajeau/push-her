import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by maaj on 2015-11-02.
 */
public class Mouvement {

    Plateau plateau;
    Case[][] boardCase;
    Case depart;
    Case arrivee;
    int poids=0;
    Convertisseur convertisseur= Convertisseur.getInstance();

    public Mouvement(Plateau plateau)
    {
        this.plateau=plateau;
        this.boardCase = plateau.board;
    }
    public Mouvement(Case depart, Case arrivee)
    {
        this.arrivee=arrivee;
        this.depart = depart;
    }

    //Fonction pour deplacer sur les cases et faire la verification
    public String deplacer(Case depart,Case arrivee)
    {
        this.depart=depart;
        this.arrivee=arrivee;

        //Verifier si ca marche


        //Enlever et ajouter des hashtable des possibilitees des couleurs
        if(depart.occupant.couleur)
        {
            plateau.casesOccupeesBlanches.remove(depart.id);
            plateau.casesOccupeesBlanches.put(arrivee.id,arrivee);
        }
        else
        {
            plateau.casesOccupeesNoires.remove(depart.id);
            plateau.casesOccupeesNoires.put(arrivee.id,arrivee);
        }

        //Deplacer dans les mapping
        arrivee.occupant=depart.occupant;
        depart.occupant=null;

        return depart.id+arrivee.id;
    }


    //Fonction pour la mise a jour des mouvements de l'adversaire dans notre board
    public void updateJoueur(String coordonner)
    {
        //Couper en deux la coordonner
        coordonner=coordonner.replaceAll("\\s","");
        coordonner=coordonner.replaceAll("[\u0000-\u001f]", "");
        String[] sousResultat=coordonner.split("-");

        Case caseDepart=(Case)plateau.listeCase.get(sousResultat[0]);
        Case caseArriver=(Case)plateau.listeCase.get(sousResultat[1]);

        deplacer(caseDepart,caseArriver);
    }
    public void validerCoup(Case depart)
    {
        Pion pion=depart.occupant;
        boolean coulJ = depart.occupant.couleur;
        String id = depart.id;
        char letter = id.charAt(0);
        int colonne = convertisseur.LettreAChiffre(letter);
        String stringLigne =  ""+id.charAt(1);
        int ligne = Integer.parseInt(stringLigne);
        ArrayList<Mouvement> arrayMouvements = new ArrayList<Mouvement>();

        int ligneTempo=ligne+1;
        int colonneTempo=colonne+1;

        System.out.println("Infomation Mouvement");
        System.out.print(id);
        System.out.println("ligne: "+ligne);
        System.out.println("Colonne: "+colonne);




    }


    /*
    * 123
    * 456
    * 789
    * */
    public ArrayList<Mouvement> coupsValides(Case depart)
    {
        Pion pion = depart.occupant;
        boolean coulJ = depart.occupant.couleur;
        String id = depart.id;
        int colonne = convertisseur.LettreAChiffre(id.charAt(0));
        int ligne = getLigneBoard(Integer.parseInt(new String(""+id.charAt(1))));
        ArrayList<Mouvement> arrayMouvements = new ArrayList<Mouvement>();


        if(pion instanceof Pousseur)
        {
            if (coulJ == true) {
                //Deplacement tout droit pour pousseur blanc
                if (boardCase[ligne - 1][colonne].occupant == null) {
                    Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne]);
                    arrayMouvements.add(m);
                }
                //Deplacement a droite pour pousseur blanc
                if (colonne != 7) {
                    if (boardCase[ligne - 1][colonne + 1].occupant == null) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne + 1]);
                        arrayMouvements.add(m);
                    }
                }
                //Deplacement a gauche pour pousseur blanc
                if (colonne != 0) {
                    if (boardCase[ligne - 1][colonne - 1].occupant == null) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne - 1]);
                        arrayMouvements.add(m);
                    }
                }
            }
            //Deplacement tout droit pour pousseur noir
            else if (coulJ == false) {
                if (boardCase[ligne + 1][colonne].occupant == null) {
                    Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne]);
                    arrayMouvements.add(m);
                }
                //Deplacement a droite pour pousseur blanc
                if (colonne != 7) {
                    if (boardCase[ligne + 1][colonne + 1].occupant == null) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne + 1]);
                        arrayMouvements.add(m);
                    }
                }
                //Deplacement a gauche pour pousseur blanc
                if (colonne != 0) {
                    if (boardCase[ligne + 1][colonne - 1].occupant == null) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne - 1]);
                        arrayMouvements.add(m);
                    }
                }
            }
        }

        else if(pion instanceof Pousse)
        {
            //Si le pousse est blanc
            if (coulJ == true) {
                //Deplacement tout droit pour pousse blanc
                try {
                    if (boardCase[ligne - 1][colonne].occupant == null && boardCase[ligne + 1][colonne].occupant instanceof Pousseur) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne]);
                        arrayMouvements.add(m);
                    }
                }
                catch (NullPointerException e){
                    System.out.println("Il n'y a pas de pousseur a la case "+ boardCase[ligne + 1][colonne].id);
                }
                //Deplacement a droite pour pousse blanc
                if (colonne < 7 && colonne != 0){
                    try {
                        if (boardCase[ligne - 1][colonne + 1].occupant == null && boardCase[ligne + 1][colonne - 1].occupant instanceof Pousseur) {
                            Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne + 1]);
                            arrayMouvements.add(m);
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Il n'y a pas pousseur a la case "+ boardCase[ligne + 1][colonne - 1].id);
                    }
                }
                //Deplacement a gauche pour pousse blanc
                if (colonne > 0 && colonne !=7){
                    try {
                        if (boardCase[ligne - 1][colonne - 1].occupant == null && boardCase[ligne + 1][colonne + 1].occupant instanceof Pousseur) {
                            Mouvement m = new Mouvement(depart, boardCase[ligne - 1][colonne - 1]);
                            arrayMouvements.add(m);
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Il n'y a pas pousseur a la case "+ boardCase[ligne + 1][colonne + 1].id);
                    }
                }
            }

            else if (coulJ == false) {
                //Deplacement tout droit pour pousse noir
                try {
                    if (boardCase[ligne + 1][colonne].occupant == null && boardCase[ligne - 1][colonne].occupant instanceof Pousseur) {
                        Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne]);
                        arrayMouvements.add(m);
                    }
                }
                catch (NullPointerException e){
                    System.out.println("Il n'y a pas pousseur a la case "+ boardCase[ligne - 1][colonne].id);
                }
                //Deplacement a droite pour pousse noir
                if (colonne < 7 && colonne !=0){
                    try {
                        if (boardCase[ligne + 1][colonne + 1].occupant == null && boardCase[ligne - 1][colonne - 1].occupant instanceof Pousseur) {
                            Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne + 1]);
                            arrayMouvements.add(m);
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Il n'y a pas d'occupant a la case "+ boardCase[ligne - 1][colonne - 1].id);
                    }
                }
                //Deplacement a gauche pour pousse noir
                if (colonne > 0 && colonne != 7){
                    try {
                        if (boardCase[ligne + 1][colonne - 1].occupant == null && boardCase[ligne - 1][colonne + 1].occupant instanceof Pousseur) {
                            Mouvement m = new Mouvement(depart, boardCase[ligne + 1][colonne - 1]);
                            arrayMouvements.add(m);
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Il n'y a pas pousseur a la case "+ boardCase[ligne - 1][colonne + 1].id);
                    }
                }
            }

        }

        return arrayMouvements;
    }

    private int getLigneBoard(int ligne)
    {
        return 8-ligne;
    }


}
