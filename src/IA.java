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

    public void choixCoup()
    {
        //genererMouvement
        //faire random
        //mettre ds arbre avec un tableau tempo
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



        Case depart=(Case)plateau.listeCase.get((this.conv.ChiffreALettre(randomInt))+Integer.toString(ligne));

        //Le -1 permet de faire un random entre -1,0,1. Donc gauche,avant,droite
        Case arriver=(Case)plateau.listeCase.get(this.conv.ChiffreALettre(randomInt+(randomGenerator.nextInt(3)-1))+Integer.toString(ligne+1));

        Mouvement mouvement=new Mouvement(this.plateau);

        mouvement.coupsValides(depart);

        String deplacement=mouvement.deplacer(depart,arriver);

        return deplacement;
        //return null;
    }
}
