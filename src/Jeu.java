import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Jeu {
    public Plateau plateau;
    public IA ia;

    public void construireIA()
    {
        this.ia=new IA(this.plateau);
    }

    public void construirePlateau(int[] board) {

        this.plateau = new Plateau(board);
    }
}
