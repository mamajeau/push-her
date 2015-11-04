import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Mouvement {

    Plateau plateau;
    Case depart;
    Case arriver;
    int poids=0;
    Hashtable convertisseur=new Hashtable();

    public Mouvement(Plateau plateau)
    {
        this.plateau=plateau;
    }

    //Fonction pour deplacer sur les cases et faire la verification
    public String deplacer(Case depart,Case arriver)
    {
        this.depart=depart;
        this.arriver=arriver;

        //Verifier si ca marche

        //Deplacer dans les mapping
        arriver.occupant=depart.occupant;
        depart.occupant=null;

        return depart.id+arriver.id;
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

}
