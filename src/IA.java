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
    boolean couleur;

    public IA(Plateau plateau)
    {
        this.plateau=plateau;
    }

    public Mouvement choixCoup()
    {

        Random randomizer = new Random();
        Noeud racine=new Noeud(plateau);
        ArrayList<Mouvement> mouvementPossible;
        Mouvement mouvementAFaire=null;

        //Creation des noeuds, futurement en recursif, mais pourlinsant a la main
        //va falloir verifier si la couleur qui doit jouer est la meme qui le IA sinon il va devoir passer son premier tour
        try
        {
            //Copie du tableau

            //Mouvement possible de l'IA
            mouvementPossible=plateau.genererMouvements(this.couleur);

            //Pour chacun des cas possible
            //On fait le mouvement dans le tableau
            //Ensuite on cree un noeud et on lajoute


            //================FONCTION QUI CREE LA RACINE========================
            //Toute les possibilitees en partant de la racine
            for (Mouvement possible:mouvementPossible)
            {
               Plateau plateauTempo=racine.plateau.clone();

                System.out.println("Test mouvement");
                System.out.println(possible.depart.id);
                System.out.println(possible.depart);
                System.out.println(possible.arrivee.id);
                plateauTempo.afficherBoard();


                Mouvement mouvementTempo=new Mouvement(plateauTempo);
                mouvementTempo.deplacer(possible.depart,possible.arrivee);
                Noeud noeudTempo=new Noeud(plateauTempo,mouvementTempo);
                racine.ajouterEnfant(noeudTempo);

                //Revert car sinon on change dans le vrai plateau car les references des cases reste
                mouvementTempo.deplacer(possible.arrivee,possible.depart);
            }


            /*=====================FONCTION QUI CREE LARBRE===============*/
            //Toute les possibilitees en partant de la racine des enfants
            //Pour tous les enfant de la racines
            for (Noeud noeudEnfant:racine.listeEnfant) {
                //On prend le plateau de lenfant
                Plateau plateauEnfant=noeudEnfant.plateau;
                //On verifie les mouvement que peut faire lennemi
                mouvementPossible=plateauEnfant.genererMouvements(!this.couleur);

                //Pour chacun des mouvements de lennemi
                for (Mouvement possible : mouvementPossible) {
                    Plateau plateauTempo=plateauEnfant.clone();
                    Mouvement mouvementTempo=new Mouvement(plateauTempo);
                    mouvementTempo.deplacer(possible.depart,possible.arrivee);
                    noeudEnfant.ajouterEnfant(new Noeud(plateauTempo,mouvementTempo));

                    //Revert car sinon on change dans le vrai plateau car les references des cases reste
                    mouvementTempo.deplacer(possible.arrivee,possible.depart);
                }
            }

            //Mouvement mouvementRandom = mouvementPossible.get(randomizer.nextInt(mouvementPossible.size()));

            //Choisir un move au hasard dans les noeuds de la racine
            Noeud noeudRandom = racine.listeEnfant.get(randomizer.nextInt(racine.listeEnfant.size()));
            mouvementAFaire=noeudRandom.mouvementFait;

            /*System.out.println("Mouvement choisit");
            System.out.println(mouvementRandom);
            System.out.println(mouvementRandom.depart.id);
            System.out.println(mouvementRandom.arrivee.id);
            System.out.println("Fin mouvement choisit");*/

        }
        catch (CloneNotSupportedException e)
        {
            System.out.println(e);
            throw new Error("Clone du tableau pete le gros!!");
        }
        return mouvementAFaire;
    }


    public String jouerCoup()
    {
        Mouvement mouvementAFaire=null;
        int ligne=2;
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(7);
        /*String depart=this.convertisseur.get(randomInt).toString();
        String arriver=this.convertisseur.get(randomInt+(randomGenerator.nextInt(3)-1) ).toString();
        depart=depart+Integer.toString(ligne);
        arriver=arriver+Integer.toString(ligne+1);
        System.out.println("depart"+depart);
        System.out.println("arriver"+arriver);
        */

        mouvementAFaire=choixCoup();

        /*Case depart=(Case)plateau.listeCase.get((this.conv.ChiffreALettre(randomInt))+Integer.toString(ligne));

        //Le -1 permet de faire un random entre -1,0,1. Donc gauche,avant,droite
        Case arriver=(Case)plateau.listeCase.get(this.conv.ChiffreALettre(randomInt+(randomGenerator.nextInt(3)-1))+Integer.toString(ligne+1));

        Mouvement mouvement=new Mouvement(this.plateau);

        mouvement.coupsValides(depart);*/

        System.out.println("info de deplacement");
        System.out.println(mouvementAFaire.depart.id);
        System.out.println(mouvementAFaire.arrivee.id);
        System.out.println("Fin");

        //Les informations sont inverser a cause du fait de reverse dans le choix de coup IMPORTANT
        String deplacement=mouvementAFaire.deplacer(mouvementAFaire.arrivee,mouvementAFaire.depart);


        return deplacement;
        //return null;
    }
}
