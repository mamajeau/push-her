import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Jeu {
    public Plateau plateau;
    private Hashtable listeCase;
    private Hashtable casesOccupeesNoires;
    private Hashtable casesOccupeesBlanches;
    public Case[][]boardCase=new Case[8][8];
    public IA ia;

    public void construireIA()
    {
        this.ia=new IA(this.plateau);
    }

    public void construirePlateau(int[][] board) {
        listeCase = new Hashtable();
        casesOccupeesBlanches = new Hashtable();
        casesOccupeesNoires = new Hashtable();
        for (int i=0; i<8; i++){
            for (int j=0; j<8 ;j++){
                if (board[i][j] == 4){
                    String id = this.getId(i,j);
                    Pion p = new Pousseur (true);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupeesBlanches.put(id, c);
                    boardCase[i][j]=c;
                }
                if (board[i][j] == 3){
                    String id = this.getId(i,j);
                    Pion p = new Pousse (true);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupeesBlanches.put(id, c);
                    boardCase[i][j]=c;
                }
                if (board[i][j] == 1){
                    String id = this.getId(i,j);
                    Pion p = new Pousse (false);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupeesNoires.put(id, c);
                    boardCase[i][j]=c;
                }
                if (board[i][j] == 2){
                    String id = this.getId(i,j);
                    Pion p = new Pousseur (false);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupeesNoires.put(id, c);
                    boardCase[i][j]=c;
                }
                if (board[i][j] == 0){
                    String id = this.getId(i,j);
                    Case c = new Case(id, null);
                    listeCase.put(id,c);
                    boardCase[i][j]=c;
                }
            }
        }
        this.plateau = new Plateau(listeCase,casesOccupeesBlanches ,casesOccupeesNoires, boardCase);
        Mouvement m = new Mouvement(plateau);
        Enumeration items = casesOccupeesBlanches.keys();
        //Code de cath pour tout checker
        /*ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
        while (items.hasMoreElements()){
            Case c =(Case) casesOccupeesBlanches.get(items.nextElement());
            ArrayList<Mouvement> tempo = m.coupValide(c);
            for (int i=0; i<tempo.size();i++) {
                mouvementsPossibles.add(tempo.get(i));
            }
        }*/
    }

    private String getId(int ligne, int colonne) {
        String id = "";
        switch (colonne){
            case 0: id += "A";
                break;
            case 1: id += "B";
                break;
            case 2: id += "C";
                break;
            case 3: id += "D";
                break;
            case 4: id += "E";
                break;
            case 5: id += "F";
                break;
            case 6: id += "G";
                break;
            case 7: id += "H";
                break;
        }
        switch (ligne){
            case 0: id += "8";
                break;
            case 1: id += "7";
                break;
            case 2: id += "6";
                break;
            case 3: id += "5";
                break;
            case 4: id += "4";
                break;
            case 5: id += "3";
                break;
            case 6: id += "2";
                break;
            case 7: id += "1";
                break;
        }
        return id;
    }
}
