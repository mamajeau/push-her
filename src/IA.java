import java.util.Hashtable;
import java.util.Random;

/**
 * Created by maaj on 2015-11-02.
 */
public class IA {
    Plateau plateau;
    Hashtable convertisseur=new Hashtable();

    public IA(Plateau plateau)
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

    public String jouerCoup()
    {
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

        Case depart=(Case)plateau.listeCase.get((this.convertisseur.get(randomInt))+Integer.toString(ligne));

        //Le -1 permet de faire un random entre -1,0,1. Donc gauche,avant,droite
        Case arriver=(Case)plateau.listeCase.get(this.convertisseur.get(randomInt+(randomGenerator.nextInt(3)-1))+Integer.toString(ligne+1));

        Mouvement mouvement=new Mouvement(depart,arriver,this.plateau);
        String deplacement=mouvement.deplacer();

        return deplacement;
    }
}
