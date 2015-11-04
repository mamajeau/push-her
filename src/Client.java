import java.io.*;
import java.net.*;
import java.sql.Time;
import java.util.concurrent.TimeUnit;


class Client {
    public static void main(String[] args) {

        Socket MyClient;
        BufferedInputStream input;
        BufferedOutputStream output;
        int[][] board = new int[8][8];
        Jeu jeu=null;
        try {
            MyClient = new Socket("localhost", 8888);
            input    = new BufferedInputStream(MyClient.getInputStream());
            output   = new BufferedOutputStream(MyClient.getOutputStream());
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while(1 == 1){
                char cmd = 0;

                cmd = (char)input.read();

                // Début de la partie en joueur blanc
                if(cmd == '1'){
                    byte[] aBuffer = new byte[1024];

                    int size = input.available();
                    //System.out.println("size " + size);
                    input.read(aBuffer,0,size);
                    String s = new String(aBuffer).trim();
                    System.out.println(s);
                    String[] boardValues;
                    boardValues = s.split(" ");
                    int x=0,y=0;
                    for(int i=0; i<boardValues.length;i++){
                        board[x][y] = Integer.parseInt(boardValues[i]);
                        x++;
                        if(x == 8){
                            x = 0;
                            y++;
                        }
                    }

                    jeu = new Jeu();
                    //Initialisation du jeu
                    jeu.construirePlateau(board);
                    jeu.construireIA();
                    //=========================

                    String coup=jeu.ia.jouerCoup();

                    System.out.println("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");
                        String move = null;
                        //move = console.readLine();
                        move=coup;
                        output.write(move.getBytes(),0,move.length());
                        output.flush();
                    }
                    // Début de la partie en joueur Noir
                    if(cmd == '2'){
                        System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
                        byte[] aBuffer = new byte[1024];

                        int size = input.available();
                        //System.out.println("size " + size);
                        input.read(aBuffer,0,size);
                        String s = new String(aBuffer).trim();
                        System.out.println(s);
                        String[] boardValues;
                        boardValues = s.split(" ");
                        int x=0,y=0;
                        for(int i=0; i<boardValues.length;i++){
                            board[x][y] = Integer.parseInt(boardValues[i]);
                            x++;
                        if(x == 8){
                            x = 0;
                            y++;
                        }
                    }
                        jeu = new Jeu();
                        //Initialisation du jeu
                        jeu.construirePlateau(board);
                        jeu.construireIA();
                        //=========================
                }


                // Le serveur demande le prochain coup
                // Le message contient aussi le dernier coup joué.
                if(cmd == '3'){
                    byte[] aBuffer = new byte[16];

                    int size = input.available();
                    //System.out.println("size " + size);
                    input.read(aBuffer,0,size);

                    String s = new String(aBuffer);

                    //Coup du joueur humain
                    Mouvement mouvementJoueur=new Mouvement(jeu.plateau);
                    mouvementJoueur.updateJoueur(s);
                    jeu.plateau.afficherBoard();

                    System.out.println("Dernier coup : "+ s);
                    System.out.println("Entrez votre coup : ");

                    String coup=jeu.ia.jouerCoup();
                    String move = null;
                    //move = console.readLine();
                    move=coup;

                    output.write(move.getBytes(),0,move.length());
                    output.flush();
                }
                // Le dernier coup est invalide
                if(cmd == '4'){
                    System.out.println("Coup invalide, entrez un nouveau coup : ");

                    String coup=jeu.ia.jouerCoup();
                    String move = null;
                    //move = console.readLine();
                    move=coup;
                    output.write(move.getBytes(),0,move.length());
                    output.flush();

                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){}

            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
}

