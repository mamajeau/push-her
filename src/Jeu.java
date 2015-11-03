import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Jeu {
    private Plateau plateau;
    private Hashtable listeCase;
    private Hashtable casesOccupees;

    public void construirePlateau(int[][] board) {
        System.out.println("construction Plateau");
        listeCase = new Hashtable();
        casesOccupees = new Hashtable();
        for (int i=0; i<8; i++){
            for (int j=0; j<8 ;j++){
                if (board[i][j] == 4){
                    String id = this.getId(i,j);
                    Pion p = new Pousseur (true);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupees.put(id, c);
                }
                if (board[i][j] == 3){
                    String id = this.getId(i,j);
                    Pion p = new Pousse (true);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupees.put(id,c);
                }
                if (board[i][j] == 1){
                    String id = this.getId(i,j);
                    Pion p = new Pousse (false);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupees.put(id,c);
                }
                if (board[i][j] == 2){
                    String id = this.getId(i,j);
                    Pion p = new Pousseur (false);
                    Case c = new Case(id, p);
                    listeCase.put(id,c);
                    casesOccupees.put(id,c);
                }
                if (board[i][j] == 0){
                    String id = this.getId(i,j);
                    Case c = new Case(id, null);
                    listeCase.put(id,c);
                }
            }
        }
        this.plateau = new Plateau(listeCase, casesOccupees, board);
    }

    private String getId(int ligne, int colonne) {
        String id = "";
        switch (ligne){
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
        switch (colonne){
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
