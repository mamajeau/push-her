import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Plateau {

    Hashtable listeCase;
    Hashtable casesOccupees;
    int[][] board;

    public Plateau(Hashtable listeCase, Hashtable casesOccupees, int[][] board){
        this.listeCase = listeCase;
        this.casesOccupees = casesOccupees;
        this.board = board;

        listeCase();
    }

    //Petite fonction qui permet de passer a travers la liste de pion et qui donne leur case et le nombre de case
    public void listeCase()
    {
        Hashtable liste=casesOccupees;
        Enumeration items = liste.keys();
        int nbPion=0;

        while(items.hasMoreElements())
        {
            String strKey = (String)items.nextElement();
            Case caseSimple=(Case)liste.get(strKey);
            String strValue=caseSimple.occupant.getClass().toString();
            nbPion+=1;

            System.out.println(strKey+" "+strValue);
        }
        System.out.println("Nombre de pion sur le tableau="+nbPion);
    }
}
