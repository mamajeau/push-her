import java.util.ArrayList;

/**
 * Created by maaj on 2015-11-07.
 */
public class Noeud {

    Plateau plateau;
    ArrayList<Noeud> listeEnfant=new ArrayList<Noeud>();
    int poids;
    Mouvement mouvementFait;

    public Noeud(Plateau plateau)
    {
        this.plateau=plateau;
    }

    public Noeud(Plateau plateau,Mouvement mouvementFait)
    {
        this.plateau=plateau;
        this.mouvementFait=mouvementFait;
    }

    public void ajouterEnfant(Noeud enfant)
    {
        listeEnfant.add(enfant);
    }
}
