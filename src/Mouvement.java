import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by maaj on 2015-11-02.
 */
public class Mouvement {

    int ligneDepart;
    int ligneArrivee;
    int colonneDepart;
    int colonneArrivee;
    int poids=0;
    Convertisseur convertisseur= Convertisseur.getInstance();

    public Mouvement(int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee)
    {
        this.ligneDepart = ligneDepart;
        this.colonneDepart = colonneDepart;
        this.ligneArrivee = ligneArrivee;
        this.colonneArrivee = colonneArrivee;
    }


    public void validerCoup(Case depart)
    {
        //Pion pion=depart.occupant;
        //boolean coulJ = depart.occupant.couleur;

        String id = depart.id;
        char letter = id.charAt(0);
        int colonne = convertisseur.LettreAChiffre(letter);
        String stringLigne =  ""+id.charAt(1);
        int ligne = Integer.parseInt(stringLigne);

        //ArrayList<Mouvement> arrayMouvements = new ArrayList<Mouvement>();

       // int ligneTempo=ligne+1;
       // int colonneTempo=colonne+1;

        System.out.println("Infomation Mouvement");
        System.out.print(id);
        System.out.println("ligne: "+ligne);
        System.out.println("Colonne: "+colonne);




    }


}
