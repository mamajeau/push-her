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
    }
}
