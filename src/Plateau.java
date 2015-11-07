import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by maaj on 2015-11-02.
 */
public class Plateau {

    Hashtable listeCase;
    Hashtable casesOccupeesBlanches;
    Hashtable casesOccupeesNoires;
    Case[][] board;
    Convertisseur conv = Convertisseur.getInstance();

    public Plateau(Hashtable listeCase, Hashtable casesOccupeesBlanches,Hashtable casesOccupeesNoires, Case[][] board){
        this.listeCase = listeCase;
        this.casesOccupeesBlanches = casesOccupeesBlanches;
        this.casesOccupeesNoires = casesOccupeesNoires;
        this.board = board;

        //afficherBoard();
        afficherCase();
    }

    //Fonction qu'on a a appeler pour generer les mouvements en fonction d'un plateau (Tu vois mamajeau jmets des commentaires)
    public void genererMouvements(){
        Mouvement m = new Mouvement(this);
        Enumeration itemsBlancs = casesOccupeesBlanches.keys();
        //G�n�ration des mouvements des pions blancs, en fonctions des cases occup�es blanches
        ArrayList<Mouvement> mouvementsPossiblesBlancs = new ArrayList<Mouvement>();
        while (itemsBlancs.hasMoreElements()){
            Case c =(Case) casesOccupeesBlanches.get(itemsBlancs.nextElement());
            ArrayList<Mouvement> tempo = m.coupsValides(c);
            for (int i=0; i<tempo.size();i++) {
                mouvementsPossiblesBlancs.add(tempo.get(i));
            }
        }
        Enumeration itemsNoirs = casesOccupeesNoires.keys();
        //G�n�ration des mouvements des pions noirs, en fonctions des cases occup�es noires
        ArrayList<Mouvement> mouvementsPossiblesNoirs = new ArrayList<Mouvement>();
        while (itemsNoirs.hasMoreElements()){
            Case c =(Case) casesOccupeesNoires.get(itemsNoirs.nextElement());
            ArrayList<Mouvement> tempo = m.coupsValides(c);
            for (int i=0; i<tempo.size();i++) {
                mouvementsPossiblesNoirs.add(tempo.get(i));
            }
        }
        String s = "hello";
        //Ici on a mouvementsPossiblesBlancs et mouvementsPossiblesNoirs qui contiennent les mouvements, on pourrait pogner les randoms et les pitcher au minmax
    }


    //petite fonction pour passer  travers le board pour afficher les cases
    public void afficherBoard()
    {
        for (int i=0; i<8; i++) {
            String ligne="";
            for (int j = 0; j < 8; j++) {
                //ligne=ligne+Integer.toString(board[j][i]);
                Case caseSimple=board[i][j];
                if (caseSimple.occupant != null)
                {
                    if (caseSimple.occupant instanceof Pousse)
                    {
                        if(caseSimple.occupant.couleur)
                        {
                            ligne+="pb";
                        }
                        else
                        {
                            ligne+="pn";
                        }
                    }
                    if (caseSimple.occupant instanceof Pousseur)
                    {
                        if(caseSimple.occupant.couleur)
                        {
                            ligne+="Pb";
                        }
                        else
                        {
                            ligne+="Pn";
                        }
                    }
                }
                else
                {
                    ligne+="nl";
                }
            }
            System.out.println(ligne);
        }
    }
    public void afficherCase() {
        for (int i = 0; i < 8; i++) {
            String ligne = "";
            for (int j = 0; j < 8; j++) {
                //ligne=ligne+Integer.toString(board[j][i]);
                Case caseSimple = board[i][j];
                ligne+=caseSimple.id;

            }
            System.out.println(ligne);
        }
    }

    //Petite fonction qui permet de passer a travers la liste de pion et qui donne leur case et le nombre de case
    public void listeCase(Hashtable listeCase)
    {
        Hashtable liste=listeCase;
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



    public Case getOpposite(Case depart, Case arriver, boolean couleurJoueur){

        int arriver_x = conv.LettreAChiffre(arriver.id.charAt(0));
        int arriver_y = Character.digit(arriver.id.charAt(1),10);

        int depart_x = conv.LettreAChiffre(depart.id.charAt(0));
        int depart_y = Character.digit(depart.id.charAt(1),10);

        int deplacementX = arriver_x - depart_x;
        int deplacementY = arriver_y - depart_y;

        if (couleurJoueur){

        if(deplacementX == -1 && deplacementY == 1)
        {
            return board[depart_x+1][depart_y-1];
        }else if (deplacementX == 0 && deplacementY == 1)
        {
            return board[depart_x][depart_y-1];
        }else if (deplacementX == 1 && deplacementY == 1)
        {
            return board[depart_x-1][depart_y-1];
        }

        }else{
            if(deplacementX == -1 && deplacementY == -1)
            {
                return board[depart_x+1][depart_y+1];
            }else if (deplacementX == 0 && deplacementY == -1)
            {
                return board[depart_x][depart_y+1];
            }else if (deplacementX == 1 && deplacementY == -1)
            {
                return board[depart_x-1][depart_y+1];
            }
        }

        return null;
    }
}
