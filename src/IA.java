import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by maaj on 2015-11-02.
 */
public class IA {
    Plateau plateau;
    Convertisseur conv = Convertisseur.getInstance();
    Noeud minMax;
    boolean couleur;
    long startTime;

    public IA(Plateau plateau)
    {
        this.plateau=plateau;
    }

    public Mouvement choixCoup()
    {
        startTime = System.currentTimeMillis();
        int[][] board = plateau.board;
        Random randomizer = new Random();

        minMax = new Noeud(board,null);
        genererNoeuds(minMax,plateau,this.couleur,0);

        Mouvement mouvementAFaire = minMax.listeEnfant.get(randomizer.nextInt(minMax.listeEnfant.size())).mouvementFait;
        return mouvementAFaire;
    }

    public void genererNoeuds(Noeud racine, Plateau p,boolean couleur,int profondeur){
        long stopTime = System.currentTimeMillis();
        //System.out.println(stopTime-startTime);
       if ((stopTime - startTime) > 4000){
            return;
        }
        ArrayList<Mouvement> mouvementsPossibles;
        mouvementsPossibles = p.genererMouvements(couleur);
        profondeur ++ ;
        for (int i = 0; i < mouvementsPossibles.size(); i++) {
            int[][] boardEnfant = p.deplacerDansBoard(mouvementsPossibles.get(i).ligneDepart, mouvementsPossibles.get(i).colonneDepart, mouvementsPossibles.get(i).ligneArrivee, mouvementsPossibles.get(i).colonneArrivee);
            racine.ajouterEnfant(new Noeud(boardEnfant, mouvementsPossibles.get(i)));
        }
        for (int i = 0; i < racine.listeEnfant.size(); i++) {
            Jeu j = new Jeu();
            j.construirePlateau(racine.listeEnfant.get(i).getBoard());
            genererNoeuds(racine.listeEnfant.get(i), j.plateau, !couleur, profondeur);
        }
       // }
}

    public String jouerCoup()
    {
        Mouvement mouvementAFaire=null;
        mouvementAFaire = choixCoup();

        //Les informations sont inverser a cause du fait de reverse dans le choix de coup IMPORTANT
        String deplacement=plateau.deplacer(mouvementAFaire.ligneDepart,mouvementAFaire.colonneDepart,mouvementAFaire.ligneArrivee,mouvementAFaire.colonneArrivee);


        return deplacement;
        //return null;
    }
}
