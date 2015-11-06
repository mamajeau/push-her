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

    public Mouvement(Plateau plateau, Case[][]boardCase)
    {
        this.plateau=plateau;
        this.boardCase = boardCase;
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

    /*
    * 123
    * 456
    * 789
    * */
    public ArrayList<Mouvement> coupValide(Case depart)
    {
        Pion pion = depart.occupant;
        boolean coulJ = depart.occupant.couleur;
        String id = depart.id;
        System.out.println(id);
        char letter = id.charAt(0);
        int colonne = convertisseur.LettreAChiffre(letter);
        String stringLigne =  ""+id.charAt(1);
        int ligne = Integer.parseInt(stringLigne);
        ArrayList<Mouvement> arrayMouvements = new ArrayList<Mouvement>();
        if(pion instanceof Pousseur)
        {
            //Si le pousseur est blanc
            if (coulJ == true){
                if (ligne<7){

                    if (colonne<7 && colonne>0){
                        if(boardCase[getPP(ligne)][getMM(colonne)].occupant!=null){
                            if (boardCase[getPP(ligne)][getMM(colonne)].occupant.couleur!=coulJ) {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                        if( boardCase[getPP(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getPP(colonne)].occupant!=null){
                            if (boardCase[getPP(ligne)][getPP(colonne)].occupant.couleur!=coulJ) {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 7){
                        if(boardCase[getPP(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getMM(colonne)].occupant!=null) {
                            if (boardCase[getPP(ligne)][getMM(colonne)].occupant.couleur != coulJ) {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 0){
                        if(boardCase[getPP(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getPP(colonne)].occupant!=null) {
                            if (boardCase[getPP(ligne)][getPP(colonne)].occupant.couleur!=coulJ){
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                }
            }
            //Le pousseur est noir
            else {
                if (ligne<0){

                    if (colonne<7 && colonne>0){
                        if(boardCase[getMM(ligne)][getMM(colonne)].occupant!=null) {
                            if (boardCase[getMM(ligne)][getMM(colonne)].occupant.couleur!=coulJ)
                            {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                        if( boardCase[getMM(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getPP(colonne)].occupant!=null) {
                             if(boardCase[getMM(ligne)][getPP(colonne)].occupant.couleur!=coulJ) {
                                 Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                                 arrayMouvements.add(m);
                             }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 7){
                        if(boardCase[getMM(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getMM(colonne)].occupant!=null){
                            if (boardCase[getMM(ligne)][getMM(colonne)].occupant.couleur!=coulJ) {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 0){
                        if(boardCase[getMM(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getPP(colonne)].occupant!=null){
                            if (boardCase[getMM(ligne)][getPP(colonne)].occupant.couleur!=coulJ) {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else {
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                }
            }
        }
        else if(pion instanceof Pousse)
        {

            //Si le pousseur est blanc
            if (coulJ == true){
                if (ligne<7){

                    if (colonne<7 && colonne>0){
                        int tempoLigne = getPP(ligne);
                        int tempoColonne = getMM(colonne);
                        Case c = boardCase[getPP(ligne)][getMM(colonne)];
                        if(boardCase[getPP(ligne)][getMM(colonne)].occupant!=null){
                                if (boardCase[getPP(ligne)][getMM(colonne)].occupant.couleur!=coulJ && boardCase[getMM(ligne)][getPP(colonne)].occupant instanceof Pousseur) {
                                    Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                                    arrayMouvements.add(m);
                                }
                        }
                        else if(boardCase[getMM(ligne)][getPP(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                        if( boardCase[getPP(ligne)][colonne].occupant == null && boardCase[getMM(ligne)][colonne].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getPP(colonne)].occupant!=null) {
                            if (boardCase[getPP(ligne)][getPP(colonne)].occupant.couleur!=coulJ && boardCase[getMM(ligne)][getMM(colonne)].occupant instanceof Pousseur)
                            {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getMM(ligne)][getMM(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 7){
                        if(boardCase[getPP(ligne)][colonne].occupant == null && boardCase[getMM(ligne)][colonne].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getMM(colonne)].occupant!=null){
                            if (boardCase[getPP(ligne)][getMM(colonne)].occupant.couleur!=coulJ && boardCase[getMM(ligne)][getPP(colonne)].occupant instanceof Pousseur)
                            {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getMM(ligne)][getPP(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 0){
                        if(boardCase[getPP(ligne)][colonne].occupant == null){
                            Mouvement m = new Mouvement(depart,boardCase[getPP(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getPP(ligne)][getPP(colonne)].occupant!=null){
                            if (boardCase[getPP(ligne)][getPP(colonne)].occupant.couleur!=coulJ && boardCase[getMM(ligne)][getMM(colonne)].occupant instanceof Pousseur) {
                                Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getMM(ligne)][getMM(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getPP(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                }
            }
            //Le pousseur est noir
            else {
                if (ligne<0){

                    if (colonne<7 && colonne>0){
                        if(boardCase[getMM(ligne)][getMM(colonne)].occupant!=null){
                                if (boardCase[getMM(ligne)][getMM(colonne)].occupant.couleur!=coulJ && boardCase[getPP(ligne)][getPP(colonne)].occupant instanceof Pousseur) {
                                    Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                                    arrayMouvements.add(m);
                                }
                        }
                        else if (boardCase[getPP(ligne)][getPP(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                        if( boardCase[getMM(ligne)][colonne].occupant == null && boardCase[getPP(ligne)][colonne].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getPP(colonne)].occupant!=null) {
                            if (boardCase[getMM(ligne)][getPP(colonne)].occupant.couleur!=coulJ && boardCase[getPP(ligne)][getMM(colonne)].occupant instanceof Pousseur) {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getMM(ligne)][getPP(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 7){
                        if(boardCase[getMM(ligne)][colonne].occupant == null && boardCase[getPP(ligne)][colonne].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getMM(colonne)].occupant!=null){
                            if (boardCase[getMM(ligne)][getMM(colonne)].occupant.couleur!=coulJ && boardCase[getPP(ligne)][getPP(colonne)].occupant instanceof Pousseur) {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getPP(ligne)][getMM(colonne)].occupant == null && boardCase[getPP(ligne)][getPP(colonne)].occupant instanceof Pousseur) {
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getMM(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                    else if (colonne == 0){
                        if(boardCase[getMM(ligne)][colonne].occupant == null && boardCase[getPP(ligne)][colonne].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart,boardCase[getMM(ligne)][colonne]);
                            arrayMouvements.add(m);
                        }
                        if(boardCase[getMM(ligne)][getPP(colonne)].occupant!=null){
                            if (boardCase[getMM(ligne)][getPP(colonne)].occupant.couleur!=coulJ && boardCase[getPP(ligne)][getMM(colonne)].occupant instanceof Pousseur) {
                                Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                                arrayMouvements.add(m);
                            }
                        }
                        else if (boardCase[getMM(ligne)][getPP(colonne)].occupant == null && boardCase[getPP(ligne)][getMM(colonne)].occupant instanceof Pousseur){
                            Mouvement m = new Mouvement(depart, boardCase[getMM(ligne)][getPP(colonne)]);
                            arrayMouvements.add(m);
                        }
                    }
                }
            }
        }

        return arrayMouvements;
    }
    private int getPP(int ligne) {
        int tempo = ligne;
        return tempo ++;
    }
    private int getMM(int ligne) {
        int tempo = ligne;
        return tempo --;
    }
}
