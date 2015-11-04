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

        this.convertisseur.put(0,"A");
        this.convertisseur.put(1,"B");
        this.convertisseur.put(2,"C");
        this.convertisseur.put(3,"D");
        this.convertisseur.put(4,"E");
        this.convertisseur.put(5,"F");
        this.convertisseur.put(6,"G");
        this.convertisseur.put(7,"H");
    }

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

    public void updateJoueur(String coordonner)
    {
        System.out.println("joueur: "+coordonner);
    }

}
